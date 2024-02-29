package com.sbm.sevenroomstohub.serdes;

import com.sbm.sevenroomstohub.domain.ClientPayload;
import com.sbm.sevenroomstohub.domain.ReservationPayload;
import com.sbm.sevenroomstohub.domain.Venue;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public final class CustomSerdes {

    private CustomSerdes() {}

    public static Serde<ClientPayload> ClientPayload() {
        JacksonSerializer<ClientPayload> serializer = new JacksonSerializer<>();
        ClientDeserializer<ClientPayload> deserializer = new ClientDeserializer<>(ClientPayload.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<ReservationPayload> ReservationPayload() {
        JacksonSerializer<ReservationPayload> serializer = new JacksonSerializer<>();
        ReservationDeserializer<ReservationPayload> deserializer = new ReservationDeserializer<>(ReservationPayload.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

    public static Serde<Venue> VenuePayload() {
        JacksonSerializer<Venue> serializer = new JacksonSerializer<>();
        VenueDeserializer deserializer = new VenueDeserializer();
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
