package com.sbm.sevenroomstohub.serdes;

import com.sbm.sevenroomstohub.domain.Client;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public final class CustomSerdes {

    private CustomSerdes() {}

    public static Serde<Client> Client() {
        JacksonSerializer<Client> serializer = new JacksonSerializer<>();
        JacksonDeserializer<Client> deserializer = new JacksonDeserializer<>(Client.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
