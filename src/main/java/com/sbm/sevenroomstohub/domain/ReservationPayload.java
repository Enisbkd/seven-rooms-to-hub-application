package com.sbm.sevenroomstohub.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbm.sevenroomstohub.service.dto.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ReservationPayload implements Serializable {

    @JsonProperty("entity")
    private ReservationDTO reservation;

    @JsonProperty("event_type")
    private String event_type;

    @JsonProperty("entity_type")
    private String entity_type;

    @JsonIgnore
    private Set<UpdateField> updates;

    private Set<ResTagDTO> resTags = new HashSet<>();

    private Set<ResPosTicketPayload> resPosTickets = new HashSet<>();

    private Set<ResCustomFieldDTO> resCustomFields = new HashSet<>();

    private Set<ResTableDTO> resTables = new HashSet<>();

    public Set<ResTagDTO> getResTags() {
        return resTags;
    }

    public void setResTags(Set<ResTagDTO> resTags) {
        this.resTags = resTags;
    }

    public Set<ResPosTicketPayload> getResPosTickets() {
        return resPosTickets;
    }

    public void setResPosTickets(Set<ResPosTicketPayload> resPosTickets) {
        this.resPosTickets = resPosTickets;
    }

    public Set<ResCustomFieldDTO> getResCustomFields() {
        return resCustomFields;
    }

    public void setResCustomFields(Set<ResCustomFieldDTO> resCustomFields) {
        this.resCustomFields = resCustomFields;
    }

    public Set<ResTableDTO> getResTables() {
        return resTables;
    }

    public void setResTables(Set<ResTableDTO> resTables) {
        this.resTables = resTables;
    }

    public ReservationDTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationDTO reservation) {
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

    public ReservationPayload(
        ReservationDTO reservation,
        String event_type,
        String entity_type,
        Set<UpdateField> updates,
        Set<ResTagDTO> resTags,
        Set<ResPosTicketPayload> resPosTickets,
        Set<ResCustomFieldDTO> resCustomFields,
        Set<ResTableDTO> resTables
    ) {
        this.reservation = reservation;
        this.event_type = event_type;
        this.entity_type = entity_type;
        this.updates = updates;
        this.resTags = resTags;
        this.resPosTickets = resPosTickets;
        this.resCustomFields = resCustomFields;
        this.resTables = resTables;
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
            ", resTags=" +
            resTags +
            ", resPosTickets=" +
            resPosTickets +
            ", resCustomFields=" +
            resCustomFields +
            ", resTables=" +
            resTables +
            '}'
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservation, event_type, entity_type, updates);
    }

    public ReservationPayload() {}
}
