//package com.sbm.sevenroomstohub.serdes;
//
//import com.fasterxml.jackson.core.JacksonException;
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sbm.sevenroomstohub.domain.ClientPayload;
//import org.apache.kafka.common.errors.SerializationException;
//import org.apache.kafka.common.serialization.Deserializer;
//
//import java.io.IOException;
//
//
//public class ClientDeserializer<ClientPayload> extends JsonDeserializer<ClientPayload> {
//
//    private final ObjectMapper objectMapper;
//
//    Class<T> cls;
//
//    public ClientDeserializer() {
//        this.objectMapper = new ObjectMapper();
//    }
//
//    @Override
//    public ClientPayload deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
//        ClientPayload clientPayload = p.readValueAs(ClientPayload.class);
//        return null;
//    }
//}
