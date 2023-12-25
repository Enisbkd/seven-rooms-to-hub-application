package com.sbm.sevenroomstohub.service;

import com.sbm.sevenroomstohub.domain.Client;
import com.sbm.sevenroomstohub.domain.Reservation;
import com.sbm.sevenroomstohub.serdes.CustomSerdes;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class streamsProcessor {

    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final Serde<Client> CLIENT_SERDE = CustomSerdes.Client();
    private static final Serde<Reservation> RESERVATION_SERDE = CustomSerdes.Reservation();

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, Client> clientStream = streamsBuilder.stream("data-7rooms-client", Consumed.with(STRING_SERDE, CLIENT_SERDE));
        clientStream.foreach((k, v) -> {
            v.setTechComment(("helloooooo"));
            System.out.println(v);
        });

        KStream<String, Reservation> reservationStream = streamsBuilder.stream(
            "data-7rooms-reservation",
            Consumed.with(STRING_SERDE, RESERVATION_SERDE)
        );
        reservationStream.foreach((k, v) -> {
            v.setTechComment(("hiiii"));
            System.out.println(v);
        });
    }
}
