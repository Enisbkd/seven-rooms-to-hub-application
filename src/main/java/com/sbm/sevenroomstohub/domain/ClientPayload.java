package com.sbm.sevenroomstohub.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbm.sevenroomstohub.service.dto.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ClientPayload implements Serializable {

    @JsonProperty("entity")
    private ClientDTO client;

    @JsonProperty("event_type")
    private String event_type;

    @JsonProperty("entity_type")
    private String entity_type;

    @JsonIgnore
    private Set<UpdateField> updates;

    private Set<CustomFieldDTO> customFields = new HashSet<>();

    private Set<ClientTagDTO> clientTags = new HashSet<>();

    private Set<ReservationDTO> reservations = new HashSet<>();

    private Set<MemberGroupDTO> memberGroups = new HashSet<>();

    private Set<BookingNameDTO> bookingNames = new HashSet<>();

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

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    public Set<CustomFieldDTO> getCustomFields() {
        return customFields;
    }

    public void setCustomFields(Set<CustomFieldDTO> customFields) {
        this.customFields = customFields;
    }

    public Set<ClientTagDTO> getClientTags() {
        return clientTags;
    }

    public void setClientTags(Set<ClientTagDTO> clientTags) {
        this.clientTags = clientTags;
    }

    public Set<ReservationDTO> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationDTO> reservations) {
        this.reservations = reservations;
    }

    public Set<MemberGroupDTO> getMemberGroups() {
        return memberGroups;
    }

    public void setMemberGroups(Set<MemberGroupDTO> memberGroups) {
        this.memberGroups = memberGroups;
    }

    public Set<BookingNameDTO> getBookingNames() {
        return bookingNames;
    }

    public void setBookingNames(Set<BookingNameDTO> bookingNames) {
        this.bookingNames = bookingNames;
    }

    public ClientPayload(
        ClientDTO client,
        Set<CustomFieldDTO> customFields,
        Set<ClientTagDTO> clientTags,
        Set<ReservationDTO> reservations,
        Set<MemberGroupDTO> memberGroups,
        Set<BookingNameDTO> bookingNames,
        String event_type,
        String entity_type,
        Set<UpdateField> updates
    ) {
        this.client = client;
        this.customFields = customFields;
        this.clientTags = clientTags;
        this.reservations = reservations;
        this.memberGroups = memberGroups;
        this.bookingNames = bookingNames;
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
            Objects.equals(client, that.client) &&
            Objects.equals(event_type, that.event_type) &&
            Objects.equals(entity_type, that.entity_type) &&
            Objects.equals(updates, that.updates)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(client, event_type, entity_type, updates);
    }

    @Override
    public String toString() {
        return (
            "ClientPayload{" +
            "client=" +
            client +
            ", customFields=" +
            customFields +
            ", clientTags=" +
            clientTags +
            ", reservations=" +
            reservations +
            ", memberGroups=" +
            memberGroups +
            ", bookingNames=" +
            bookingNames +
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
