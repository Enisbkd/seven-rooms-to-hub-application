package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.domain.Reservation;
import com.sbm.sevenroomstohub.domain.ReservationPayload;
import com.sbm.sevenroomstohub.serdes.CustomSerdes;
import com.sbm.sevenroomstohub.service.dto.ReservationDTO;
import com.sbm.sevenroomstohub.service.mapper.ReservationMapperImpl;
import com.sbm.sevenroomstohub.web.rest.UserResource;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.errors.StreamsException;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StreamsProcessor {

    private final Logger logger = LoggerFactory.getLogger(UserResource.class);

    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final Serde<ClientPayload> CLIENT_PAYLOAD_SERDE = CustomSerdes.ClientPayload();
    private static final Serde<ReservationPayload> RESERVATION_PAYLOAD_SERDE = CustomSerdes.ReservationPayload();

    @Value(value = "${spring.kafka.streams.client-topic}")
    private String clientTopic;

    @Value(value = "${spring.kafka.streams.reservation-topic}")
    private String reservationTopic;

    @Autowired
    ReservationService reservationService;

    @Autowired
    ReservationMapperImpl reservationMapper;

    @Autowired
    ClientPersistenceService clientPersistenceService;

    public StreamsProcessor() {}

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, ClientPayload> clientStream = streamsBuilder.stream(clientTopic, Consumed.with(STRING_SERDE, CLIENT_PAYLOAD_SERDE));
        clientStream.foreach((key, clientPayload) -> {
            clientsProcessor(clientPayload);
        });

        KStream<String, ReservationPayload> reservationStream = streamsBuilder.stream(
            reservationTopic,
            Consumed.with(STRING_SERDE, RESERVATION_PAYLOAD_SERDE)
        );
        reservationStream.foreach((key, value) -> reservationsProcessor(key, value));
    }

    private void clientsProcessor(ClientPayload clientPayload) {
        try {
            switch (clientPayload.getEvent_type()) {
                case "created":
                    {
                        clientPersistenceService.saveClient(clientPayload);
                    }
                case "updated":
                    {}
                case "deleted":
                    {}
            }
            logger.info(clientPayload.getClientDTO().toString());
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getClass());
        }
    }

    private void reservationsProcessor(String key, ReservationPayload reservationPayload) {
        try {
            logger.info(reservationPayload.toString());

            Reservation reservation = reservationPayload.getReservation();
            String clientId = reservation.getClient().getClientId(); //Check if not null && clientService findbyId

            ReservationDTO reservationDTO = reservationMapper.toDto(reservation);

            reservationService.save(reservationDTO);
        } catch (Exception e) {
            logger.error("Exception", e);
        }
    }
}
