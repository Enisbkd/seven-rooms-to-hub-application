package com.sbm.sevenroomstohub.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Objects;

public class ClientPayload implements Serializable {

    @JsonProperty("entity")
    private Client client;

    @JsonProperty("event_type")
    private String event_type;

    @JsonProperty("entity_type")
    private String entity_type;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getEvent_type() {
        return event_type;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public String getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(String entity_type) {
        this.entity_type = entity_type;
    }

    @Override
    public String toString() {
        return "ClientPayload{" + "client=" + client + ", event_type='" + event_type + '\'' + ", entity_type='" + entity_type + '\'' + '}';
    }

    public ClientPayload() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientPayload that = (ClientPayload) o;
        return (
            Objects.equals(client, that.client) &&
            Objects.equals(event_type, that.event_type) &&
            Objects.equals(entity_type, that.entity_type)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, event_type, entity_type);
    }

    public ClientPayload(Client client, String event_type, String entity_type) {
        this.client = client;
        this.event_type = event_type;
        this.entity_type = entity_type;
    }
}
