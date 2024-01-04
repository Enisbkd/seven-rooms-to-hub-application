package com.sbm.sevenroomstohub.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.sbm.sevenroomstohub.domain.BookingName} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BookingNameDTO implements Serializable {

    private Long id;

    private String name;

    private ClientVenueStatsDTO clientVenueStats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientVenueStatsDTO getClientVenueStats() {
        return clientVenueStats;
    }

    public void setClientVenueStats(ClientVenueStatsDTO clientVenueStats) {
        this.clientVenueStats = clientVenueStats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookingNameDTO)) {
            return false;
        }

        BookingNameDTO bookingNameDTO = (BookingNameDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, bookingNameDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BookingNameDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", clientVenueStats=" + getClientVenueStats() +
            "}";
    }
}
