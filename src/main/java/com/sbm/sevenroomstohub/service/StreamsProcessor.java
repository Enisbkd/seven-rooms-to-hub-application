package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.domain.ReservationPayload;
import com.sbm.sevenroomstohub.domain.Venue;
import com.sbm.sevenroomstohub.serdes.CustomSerdes;
import com.sbm.sevenroomstohub.serdes.VenueDeserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StreamsProcessor {

    private final Logger logger = LoggerFactory.getLogger(StreamsProcessor.class);

    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final Serde<ClientPayload> CLIENT_PAYLOAD_SERDE = CustomSerdes.ClientPayload();
    private static final Serde<ReservationPayload> RESERVATION_PAYLOAD_SERDE = CustomSerdes.ReservationPayload();
    private static final Serde<Venue> VENUE_PAYLOAD_SERDE = CustomSerdes.VenuePayload();

    @Value(value = "${spring.kafka.topics.client-topic}")
    private String clientTopic;

    @Value(value = "${spring.kafka.topics.reservation-topic}")
    private String reservationTopic;

    @Value(value = "${spring.kafka.topics.venue-topic}")
    private String venueTopic;

    @Autowired
    ClientPersistenceService clientPersistenceService;

    @Autowired
    ReservationPersistenceService reservationPersistenceService;

    @Autowired
    VenuePersistenceService venuePersistenceService;

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

        KStream<String, Venue> venueStream = streamsBuilder.stream(venueTopic, Consumed.with(STRING_SERDE, VENUE_PAYLOAD_SERDE));
        venueStream.foreach((key, value) -> venuesProcessor(value));
    }

    private void clientsProcessor(ClientPayload clientPayload) {
        try {
            if (clientPayload != null) {
                clientPersistenceService.upsertClient(clientPayload);
            } else {
                logger.info("ClientPayload Empty , Aborting ...");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getClass());
        }
    }

    private void reservationsProcessor(ReservationPayload reservationPayload) {
        try {
            if (reservationPayload != null) {
                reservationPersistenceService.upsertReservation(reservationPayload);
            } else {
                logger.info("ReservationPayload Empty , Aborting ...");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getClass());
        }
    }

    private void venuesProcessor(Venue venuePayload) {
        try {
            if (venuePayload != null) {
                venuePersistenceService.createVenue(venuePayload);
            } else {
                logger.info("venuePayload Empty , Aborting ...");
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e.getClass());
        }
    }
}
