package com.sbm.sevenroomstohub.serdes;

import com.sbm.sevenroomstohub.domain.Client;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;

public final class CustomSerdes {

    private CustomSerdes() {}

    public static Serde<Client> Client() {
        JsonSerializer<Client> serializer = new JsonSerializer<>();
        JsonDeserializer<Client> deserializer = new JsonDeserializer<>(Client.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }
}
