package com.sbm.sevenroomstohub.serdes;

import com.sbm.sevenroomstohub.domain.Client;
import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.domain.Reservation;
import com.sbm.sevenroomstohub.domain.ReservationPayload;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public final class CustomSerdes {

    private CustomSerdes() {}

    public static Serde<ClientPayload> ClientPayload() {
        JacksonSerializer<ClientPayload> serializer = new JacksonSerializer<>();
        JacksonDeserializer<ClientPayload> deserializer = new JacksonDeserializer<>(ClientPayload.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<ReservationPayload> ReservationPayload() {
        JacksonSerializer<ReservationPayload> serializer = new JacksonSerializer<>();
        JacksonDeserializer<ReservationPayload> deserializer = new JacksonDeserializer<>(ReservationPayload.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
