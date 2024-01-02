package com.sbm.sevenroomstohub.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbm.sevenroomstohub.service.dto.ClientDTO;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class ClientPayload implements Serializable {

    @JsonProperty("entity")
    private ClientDTO clientDTO;

    @JsonProperty("event_type")
    private String event_type;

    @JsonProperty("entity_type")
    private String entity_type;

    @JsonIgnore
    private Set<UpdateField> updates;

    public ClientDTO getClientDTO() {
        return clientDTO;
    }

    public void setClientDTO(ClientDTO clientDTO) {
        this.clientDTO = clientDTO;
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

    public Set<UpdateField> getUpdates() {
        return updates;
    }

    public void setUpdates(Set<UpdateField> updates) {
        this.updates = updates;
    }

    public ClientPayload(ClientDTO clientDTO, String event_type, String entity_type, Set<UpdateField> updates) {
        this.clientDTO = clientDTO;
        this.event_type = event_type;
        this.entity_type = entity_type;
        this.updates = updates;
    }

    public ClientPayload() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientPayload that = (ClientPayload) o;
        return (
            Objects.equals(clientDTO, that.clientDTO) &&
            Objects.equals(event_type, that.event_type) &&
            Objects.equals(entity_type, that.entity_type) &&
            Objects.equals(updates, that.updates)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientDTO, event_type, entity_type, updates);
    }

    @Override
    public String toString() {
        return (
            "ClientPayload{" +
            "clientDTO=" +
            clientDTO +
            ", event_type='" +
            event_type +
            '\'' +
            ", entity_type='" +
            entity_type +
            '\'' +
            ", updates=" +
            updates +
            '}'
        );
    }
}
