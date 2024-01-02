package com.sbm.sevenroomstohub.serdes;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbm.sevenroomstohub.domain.Client;
import com.sbm.sevenroomstohub.domain.ClientPhoto;
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

            String userId = String.valueOf(objectMapper.readTree(bytes).get("entity").get("user").get("id"));
            String userName = String.valueOf(objectMapper.readTree(bytes).get("entity").get("user").get("name"));

            client.setUserId(userId);
            client.setUserName(userName);

            Integer cropx = Integer.parseInt(String.valueOf(objectMapper.readTree(bytes).get("entity").get("photo_crop_info").get("x")));
            Integer cropy = Integer.valueOf(String.valueOf(objectMapper.readTree(bytes).get("entity").get("photo_crop_info").get("y")));
            Double cropHeight = Double.valueOf(
                String.valueOf(objectMapper.readTree(bytes).get("entity").get("photo_crop_info").get("height"))
            );
            Double cropWidth = Double.valueOf(
                String.valueOf(objectMapper.readTree(bytes).get("entity").get("photo_crop_info").get("width"))
            );

            if (client.getClientPhoto() == null) {
                ClientPhoto clientPhoto = new ClientPhoto();
                client.setClientPhoto(clientPhoto);
            }

            client.getClientPhoto().setCropx(cropx);
            client.getClientPhoto().setCropy(cropy);
            client.getClientPhoto().setCropHeight(cropHeight);
            client.getClientPhoto().setCropWidth(cropWidth);

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
