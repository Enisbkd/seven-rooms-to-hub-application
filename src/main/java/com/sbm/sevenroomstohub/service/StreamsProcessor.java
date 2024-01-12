package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.domain.ReservationPayload;
import com.sbm.sevenroomstohub.serdes.CustomSerdes;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class StreamsProcessor {

    private final Logger logger = LoggerFactory.getLogger(StreamsProcessor.class);

    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final Serde<ClientPayload> CLIENT_PAYLOAD_SERDE = CustomSerdes.ClientPayload();
    private static final Serde<ReservationPayload> RESERVATION_PAYLOAD_SERDE = CustomSerdes.ReservationPayload();

    @Value(value = "${spring.kafka.streams.client-topic}")
    private String clientTopic;

    @Value(value = "${spring.kafka.streams.reservation-topic}")
    private String reservationTopic;

    @Autowired
    ClientPersistenceService clientPersistenceService;

    @Autowired
    ReservationPersistenceService reservationPersistenceService;

    public StreamsProcessor() {}

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, ClientPayload> clientStream = streamsBuilder.stream(clientTopic, Consumed.with(STRING_SERDE, CLIENT_PAYLOAD_SERDE));
        clientStream.foreach((key, clientPayload) -> clientsProcessor(clientPayload));

        KStream<String, ReservationPayload> reservationStream = streamsBuilder.stream(
            reservationTopic,
            Consumed.with(STRING_SERDE, RESERVATION_PAYLOAD_SERDE)
        );
        reservationStream.foreach((key, value) -> reservationsProcessor(value));
    }

    private void clientsProcessor(ClientPayload clientPayload) {
        try {
            if (clientPayload != null) {
                switch (clientPayload.getEvent_type()) {
                    case "created", "updated":
                        clientPersistenceService.upsertClient(clientPayload);
                        break;
                    case "deleted":
                        clientPersistenceService.deleteClient(clientPayload);
                        break;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getClass());
        }
    }

    private void reservationsProcessor(ReservationPayload reservationPayload) {
        try {
            if (reservationPayload != null) {
                switch (reservationPayload.getEvent_type()) {
                    case "created", "updated":
                        reservationPersistenceService.upsertReservation(reservationPayload);
                        break;
                    case "deleted":
                        reservationPersistenceService.deleteReservation(reservationPayload);
                        break;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getClass());
        }
    }
}
