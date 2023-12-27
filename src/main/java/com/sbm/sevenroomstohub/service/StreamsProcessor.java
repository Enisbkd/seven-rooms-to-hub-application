package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.domain.ReservationPayload;
import com.sbm.sevenroomstohub.repository.ClientRepository;
import com.sbm.sevenroomstohub.serdes.CustomSerdes;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.errors.StreamsException;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StreamsProcessor {

    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final Serde<ClientPayload> CLIENT_PAYLOAD_SERDE = CustomSerdes.ClientPayload();
    private static final Serde<ReservationPayload> RESERVATION_PAYLOAD_SERDE = CustomSerdes.ReservationPayload();

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, ClientPayload> clientStream = streamsBuilder.stream(
            "data-7rooms-client",
            Consumed.with(STRING_SERDE, CLIENT_PAYLOAD_SERDE)
        );
        clientStream.foreach((key, clientPayload) -> {
            try {
                if (clientPayload.getEvent_type().equalsIgnoreCase("created")) {
                    clientPayload.getClient().setTechComment("helloo");
                    clientRepository.save(clientPayload.getClient());
                    System.out.println(clientPayload.getClient());
                }
            } catch (StreamsException streamsException) {
                System.out.println(streamsException);
            }
        });

        KStream<String, ReservationPayload> reservationStream = streamsBuilder.stream(
            "data-7rooms-reservation",
            Consumed.with(STRING_SERDE, RESERVATION_PAYLOAD_SERDE)
        );
        reservationStream.foreach((key, reservationPayload) -> {
            reservationPayload.getReservation().setTechComment(("hiiii"));
            System.out.println(reservationPayload);
        });
    }
}
