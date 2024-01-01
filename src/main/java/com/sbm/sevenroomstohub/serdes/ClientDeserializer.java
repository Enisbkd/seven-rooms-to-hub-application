package com.sbm.sevenroomstohub.serdes;

/**
 * Copyright © 2017 Jeremy Custenborder (jcustenborder@gmail.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.sevenroomstohub.domain.Client;
import com.sbm.sevenroomstohub.domain.ClientVenueStats;
import java.io.IOException;
import java.util.Map;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientDeserializer<ClientPayload> implements Deserializer<ClientPayload> {

    private static final Logger log = LoggerFactory.getLogger(JacksonDeserializer.class);
    private final ObjectMapper objectMapper;
    Class<ClientPayload> cls;
    private JacksonDeserializerConfig config;

    public ClientDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    public ClientDeserializer(Class<ClientPayload> cls) {
        this();
        this.cls = cls;
    }

    public static Map<String, String> nonDefaultSettings(ObjectMapper objectMapper) {
        return JacksonDeserializerConfig.nonDefaultSettings(objectMapper);
    }

    @Override
    public void configure(Map<String, ?> settings, boolean isKey) {
        this.config = new JacksonDeserializerConfig(settings);
        this.config.configure(this.objectMapper);
        if (null != this.cls) {
            log.trace("cls is already configured to {}", this.cls.getName());
        } else {
            this.cls = this.config.outputClass;
        }
    }

    @Override
    public ClientPayload deserialize(String topic, byte[] bytes) {
        if (null == bytes) {
            return null;
        }

        try {
            com.sbm.sevenroomstohub.domain.ClientPayload clientPayload = objectMapper.readValue(
                bytes,
                com.sbm.sevenroomstohub.domain.ClientPayload.class
            );
            Client client = objectMapper.readValue(bytes, com.sbm.sevenroomstohub.domain.ClientPayload.class).getClient();

            JsonNode venue_stats = objectMapper.readTree(bytes).get("entity").get("venue_stats");

            if (venue_stats.fieldNames().hasNext()) {
                String venue_field_name = venue_stats.fieldNames().next();

                JsonNode clientVenueStatsNode = venue_stats.get(venue_field_name);

                ClientVenueStats clientVenueStats = objectMapper.convertValue(clientVenueStatsNode, ClientVenueStats.class);

                client.setClientVenueStats(clientVenueStats);
            }
            clientPayload.setClient(client);

            return (ClientPayload) clientPayload;
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public void close() {}
}
