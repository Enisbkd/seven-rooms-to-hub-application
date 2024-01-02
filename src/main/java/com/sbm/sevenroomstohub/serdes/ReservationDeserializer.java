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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.sevenroomstohub.domain.Client;
import com.sbm.sevenroomstohub.domain.ClientPhoto;
import com.sbm.sevenroomstohub.domain.Reservation;
import java.io.IOException;
import java.util.Map;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReservationDeserializer<ReservationPayload> implements Deserializer<ReservationPayload> {

    private static final Logger log = LoggerFactory.getLogger(JacksonDeserializer.class);
    private final ObjectMapper objectMapper;
    Class<ReservationPayload> cls;
    private JacksonDeserializerConfig config;

    public ReservationDeserializer() {
        this.objectMapper = new ObjectMapper();
    }

    public ReservationDeserializer(Class<ReservationPayload> cls) {
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
    public ReservationPayload deserialize(String topic, byte[] bytes) {
        if (null == bytes) {
            return null;
        }

        try {
            com.sbm.sevenroomstohub.domain.ReservationPayload reservationPayload = objectMapper.readValue(
                bytes,
                com.sbm.sevenroomstohub.domain.ReservationPayload.class
            );
            Reservation reservation = objectMapper
                .readValue(bytes, com.sbm.sevenroomstohub.domain.ReservationPayload.class)
                .getReservation();

            String clientId = String.valueOf(objectMapper.readTree(bytes).get("entity").get("client_id"));
            Client client = new Client();
            client.setClientId(clientId);
            reservation.setClient(client);

            String userId = String.valueOf(objectMapper.readTree(bytes).get("entity").get("user").get("id"));
            String userName = String.valueOf(objectMapper.readTree(bytes).get("entity").get("user").get("name"));

            reservation.setUserId(userId);
            reservation.setUserName(userName);
            reservationPayload.setReservation(reservation);
            return (ReservationPayload) reservationPayload;
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public void close() {}
}
