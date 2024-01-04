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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.sevenroomstohub.service.dto.*;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
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

            ReservationDTO reservation = reservationPayload.getReservation();

            JsonNode resEntity = objectMapper.readTree(bytes).get("entity");

            if (resEntity != null) {
                userDeserializer(resEntity, reservation);
                tagsDeserializer(resEntity, reservationPayload);
                posTicketsDeserializer(resEntity, reservationPayload);
                customFieldsDeserializer(resEntity, reservationPayload);
                tableNumbersDeserializer(resEntity, reservationPayload);

                String clientId = String.valueOf(resEntity.get("client_id"));
                ClientDTO client = new ClientDTO();
                client.setClientId(clientId);
                reservation.setClient(client);

                reservationPayload.setReservation(reservation);
            }
            return (ReservationPayload) reservationPayload;
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    private void tableNumbersDeserializer(JsonNode resEntity, com.sbm.sevenroomstohub.domain.ReservationPayload reservationPayload) {
        JsonNode tableNumbersNode = resEntity.get("table_numbers");
        if (tableNumbersNode != null) {
            Set<ResTableDTO> tableNumbers = objectMapper.convertValue(tableNumbersNode, new TypeReference<Set<ResTableDTO>>() {});
            reservationPayload.setResTables(tableNumbers);
        }
    }

    private void customFieldsDeserializer(JsonNode resEntity, com.sbm.sevenroomstohub.domain.ReservationPayload reservationPayload) {
        JsonNode customFieldsNode = resEntity.get("custom_fields");
        if (customFieldsNode != null) {
            Set<ResCustomFieldDTO> customFields = objectMapper.convertValue(
                customFieldsNode,
                new TypeReference<Set<ResCustomFieldDTO>>() {}
            );
            reservationPayload.setResCustomFields(customFields);
        }
    }

    private void posTicketsDeserializer(JsonNode resEntity, com.sbm.sevenroomstohub.domain.ReservationPayload reservationPayload) {
        JsonNode posTicketsNode = resEntity.get("pos_tickets");
        if (posTicketsNode != null) {
            Set<ResPosTicketDTO> posTickets = objectMapper.convertValue(posTicketsNode, new TypeReference<Set<ResPosTicketDTO>>() {});
            reservationPayload.setResPosTickets(posTickets);
            JsonNode itemsNode = posTicketsNode.get("items");
            if (itemsNode != null) {
                Set<ResPosticketsItemDTO> items = objectMapper.convertValue(itemsNode, new TypeReference<Set<ResPosticketsItemDTO>>() {});
                reservationPayload.setResPosticketsItems(items);
            }
        }
    }

    private void tagsDeserializer(JsonNode resEntity, com.sbm.sevenroomstohub.domain.ReservationPayload reservationPayload) {
        JsonNode tagsNode = resEntity.get("tags");
        if (tagsNode != null) {
            Set<ResTagDTO> tags = objectMapper.convertValue(tagsNode, new TypeReference<Set<ResTagDTO>>() {});
            reservationPayload.setResTags(tags);
        }
    }

    private static void userDeserializer(JsonNode resEntity, ReservationDTO reservation) {
        JsonNode userNode = resEntity.get("user");
        if (userNode != null) {
            String userId = String.valueOf(userNode.get("id"));
            String userName = String.valueOf(userNode.get("name"));
            reservation.setUserId(userId);
            reservation.setUserName(userName);
        }
    }

    @Override
    public void close() {}
}
