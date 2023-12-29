package com.sbm.sevenroomstohub.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

public class ReservationPayload implements Serializable {

    @JsonProperty("entity")
    private Reservation reservation;

    @JsonProperty("event_type")
    private String event_type;

    @JsonProperty("entity_type")
    private String entity_type;

    @JsonIgnore
    private Set<UpdateField> updates;

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationPayload that = (ReservationPayload) o;
        return (
            Objects.equals(reservation, that.reservation) &&
            Objects.equals(event_type, that.event_type) &&
            Objects.equals(entity_type, that.entity_type) &&
            Objects.equals(updates, that.updates)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservation, event_type, entity_type, updates);
    }

    @Override
    public String toString() {
        return (
            "ReservationPayload{" +
            "reservation=" +
            reservation +
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

    public ReservationPayload() {}
}
