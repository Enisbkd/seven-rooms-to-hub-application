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
import com.sbm.sevenroomstohub.domain.Reservation;
import com.sbm.sevenroomstohub.exceptions.BadEntityTypeException;
import com.sbm.sevenroomstohub.exceptions.BadEventTypeException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.streams.errors.StreamsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReservationDeserializer<ReservationPayload> implements Deserializer<ReservationPayload> {

    private static final Logger log = LoggerFactory.getLogger(ReservationDeserializer.class);
    private final ObjectMapper objectMapper;
    Class<ReservationPayload> cls;
    private JacksonDeserializerConfig config;

    List<String> eventTypes = Arrays.asList(new String[] { "created", "updated", "deleted" });

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
            String entityType = String.valueOf(objectMapper.readTree(bytes).get("entity_type"));
            //Removing quotes because field is parsed with quotes.
            String eventType = String.valueOf(objectMapper.readTree(bytes).get("event_type")).replace("\"", "");
            if (entityType.contains("reservation")) {
                if (eventTypes.contains(eventType)) {
                    com.sbm.sevenroomstohub.domain.ReservationPayload reservationPayload = objectMapper.readValue(
                        bytes,
                        com.sbm.sevenroomstohub.domain.ReservationPayload.class
                    );
                    Reservation reservation = reservationPayload.getReservation();

                    JsonNode resEntity = objectMapper.readTree(bytes).get("entity");

                    if (resEntity != null) {
                        userDeserializer(resEntity, reservation);
                        Client client = new Client();
                        String clientId = String.valueOf(resEntity.get("client_id"));
                        client.setClientId(clientId);
                        client = setClientFromResrvation(reservationPayload);

                        reservation.setClient(client);

                        reservationPayload.setReservation(reservation);
                    }
                    return (ReservationPayload) reservationPayload;
                } else throw new BadEventTypeException(
                    "Event type is not recognized , accepted values : " + eventTypes.toString() + " found :" + eventType
                );
            } else throw new BadEntityTypeException("Entity type is not Reservation , expected : Reservation , found :" + entityType);
        } catch (IOException | BadEntityTypeException | StreamsException | BadEventTypeException e) {
            throw new SerializationException(e);
        }
    }

    private static void userDeserializer(JsonNode resEntity, Reservation reservation) {
        JsonNode userNode = resEntity.get("user");
        if (userNode != null) {
            String userId = String.valueOf(userNode.get("id"));
            String userName = String.valueOf(userNode.get("name"));
            reservation.setUserId(userId);
            reservation.setUserName(userName);
        }
    }

    private Client setClientFromResrvation(com.sbm.sevenroomstohub.domain.ReservationPayload reservationPayload) {
        Client client = new Client();
        Reservation reservationEntity = reservationPayload.getReservation();
        client.setClientId(reservationEntity.getClient().getClientId());
        client.setUserId(reservationEntity.getUserId());
        client.setUserName(reservationEntity.getUserName());
        client.setLastname(reservationEntity.getLastname());
        client.setFirstname(reservationEntity.getFirstname());
        client.setEmail(reservationEntity.getEmail());
        client.setPostalCode(reservationEntity.getPostalCode());
        client.setVenueGroupId(reservationEntity.getVenueGroupId());
        client.setReferenceCode(reservationEntity.getClientReferenceCode());
        client.setAddress(reservationEntity.getAddress());
        client.setAddress2(reservationEntity.getAddress2());
        client.setPhoneNumber(reservationEntity.getPhoneNumber());
        client.setLoyaltyId(reservationEntity.getLoyaltyId());
        client.setLoyaltyRank(reservationEntity.getLoyaltyRank());
        client.setLoyaltyTier(reservationEntity.getLoyaltyTier());
        client.setCity(reservationEntity.getCity());
        client.setCountry(reservationEntity.getCountry());
        client.setState(reservationEntity.getState());
        return client;
    }

    @Override
    public void close() {}
}
