package com.sbm.sevenroomstohub.serdes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.sevenroomstohub.domain.BookingName;
import com.sbm.sevenroomstohub.domain.Client;
import com.sbm.sevenroomstohub.domain.ClientPhoto;
import com.sbm.sevenroomstohub.domain.ClientVenueStats;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
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
            Client client = clientPayload.getClient();

            JsonNode clientEntity = objectMapper.readTree(bytes).get("entity");
            if (clientEntity != null) {
                userDeserializer(clientEntity, client);
                photoCropDeserializer(clientEntity, client);
                venueStatsDeserializer(clientEntity, client);
                clientPayload.setClient(client);
            }
            return (ClientPayload) clientPayload;
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    private static void userDeserializer(JsonNode clientEntity, Client client) {
        JsonNode userNode = clientEntity.get("user");
        if (userNode != null) {
            String userId = String.valueOf(clientEntity.get("user").get("id"));
            String userName = String.valueOf(clientEntity.get("user").get("name"));
            client.setUserId(userId);
            client.setUserName(userName);
        }
    }

    private static void photoCropDeserializer(JsonNode clientEntity, Client client) {
        JsonNode photoCropNode = clientEntity.get("photo_crop_info");
        if (photoCropNode != null && !photoCropNode.isNull() && !photoCropNode.isEmpty()) {
            Integer cropX = (photoCropNode.get("x") == null) ? null : Integer.parseInt(String.valueOf(photoCropNode.get("x")));
            Integer cropY = (photoCropNode.get("y") == null) ? null : Integer.parseInt(String.valueOf(photoCropNode.get("y")));
            Double cropHeight = (photoCropNode.get("height") == null) ? null : Double.valueOf(String.valueOf(photoCropNode.get("height")));
            Double cropWidth = (photoCropNode.get("width") == null) ? null : Double.valueOf(String.valueOf(photoCropNode.get("width")));

            if (client.getClientPhoto() == null) {
                ClientPhoto clientPhoto = new ClientPhoto();
                client.setClientPhoto(clientPhoto);
            }

            client.getClientPhoto().setCropx(cropX);
            client.getClientPhoto().setCropy(cropY);
            client.getClientPhoto().setCropHeight(cropHeight);
            client.getClientPhoto().setCropWidth(cropWidth);
        }
    }

    private void venueStatsDeserializer(JsonNode clientEntity, Client client) {
        JsonNode venue_stats = clientEntity.get("venue_stats");
        if (venue_stats.fieldNames().hasNext()) {
            String venue_field_name = venue_stats.fieldNames().next();

            JsonNode clientVenueStatsNode = venue_stats.get(venue_field_name);

            ClientVenueStats clientVenueStats = objectMapper.convertValue(clientVenueStatsNode, ClientVenueStats.class);

            client.setClientVenueStats(clientVenueStats);
        }
    }

    @Override
    public void close() {}
}
