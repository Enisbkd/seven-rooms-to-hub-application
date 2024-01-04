package com.sbm.sevenroomstohub.serdes;

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
            ClientDTO clientDTO = objectMapper.readValue(bytes, com.sbm.sevenroomstohub.domain.ClientPayload.class).getClient();
            JsonNode clientEntity = objectMapper.readTree(bytes).get("entity");
            if (clientEntity != null) {
                JsonNode userNode = clientEntity.get("user");
                if (userNode != null) {
                    String userId = String.valueOf(clientEntity.get("user").get("id"));
                    String userName = String.valueOf(clientEntity.get("user").get("name"));
                    clientDTO.setUserId(userId);
                    clientDTO.setUserName(userName);
                }
                JsonNode photoCropNode = clientEntity.get("photo_crop_info");
                if (photoCropNode != null) {
                    Integer cropx = (photoCropNode.get("x") == null) ? null : Integer.parseInt(String.valueOf(photoCropNode.get("x")));
                    Integer cropy = (photoCropNode.get("y") == null) ? null : Integer.parseInt(String.valueOf(photoCropNode.get("y")));
                    Double cropHeight = (photoCropNode.get("height") == null)
                        ? null
                        : Double.valueOf(String.valueOf(photoCropNode.get("height")));
                    Double cropWidth = (photoCropNode.get("width") == null)
                        ? null
                        : Double.valueOf(String.valueOf(photoCropNode.get("width")));

                    if (clientDTO.getClientPhoto() == null) {
                        log.info("creating new PHOTO");
                        ClientPhotoDTO clientPhoto = new ClientPhotoDTO();
                        clientDTO.setClientPhoto(clientPhoto);
                    }

                    clientDTO.getClientPhoto().setCropx(cropx);
                    clientDTO.getClientPhoto().setCropy(cropy);
                    clientDTO.getClientPhoto().setCropHeight(cropHeight);
                    clientDTO.getClientPhoto().setCropWidth(cropWidth);
                }
                JsonNode clientTagsNode = clientEntity.get("client_tags");
                if (clientTagsNode != null) {
                    Set<ClientTagDTO> clientTags = objectMapper.convertValue(clientTagsNode, new TypeReference<Set<ClientTagDTO>>() {});
                    clientPayload.setClientTags(clientTags);
                }
                JsonNode customFieldsNode = clientEntity.get("custom_fields");
                if (customFieldsNode != null) {
                    Set<CustomFieldDTO> customFields = objectMapper.convertValue(
                        customFieldsNode,
                        new TypeReference<Set<CustomFieldDTO>>() {}
                    );
                    clientPayload.setCustomFields(customFields);
                }
                JsonNode memberGroupsNode = clientEntity.get("member_groups");
                if (memberGroupsNode != null) {
                    Set<MemberGroupDTO> memberGroups = objectMapper.convertValue(
                        memberGroupsNode,
                        new TypeReference<Set<MemberGroupDTO>>() {}
                    );
                    clientPayload.setMemberGroups(memberGroups);
                }

                JsonNode venue_stats = clientEntity.get("venue_stats");

                if (venue_stats.fieldNames().hasNext()) {
                    String venue_field_name = venue_stats.fieldNames().next();

                    JsonNode clientVenueStatsNode = venue_stats.get(venue_field_name);

                    ClientVenueStatsDTO clientVenueStats = objectMapper.convertValue(clientVenueStatsNode, ClientVenueStatsDTO.class);

                    JsonNode bookedByNamesNode = clientVenueStatsNode.get("booked_by_names");

                    if (bookedByNamesNode != null) {
                        Set bookedByNames = objectMapper.convertValue(bookedByNamesNode, Set.class);
                        clientPayload.setBookingNames(bookedByNames);
                    }

                    clientDTO.setClientVenueStats(clientVenueStats);
                }
                clientPayload.setClient(clientDTO);
            }
            return (ClientPayload) clientPayload;
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    @Override
    public void close() {}
}
