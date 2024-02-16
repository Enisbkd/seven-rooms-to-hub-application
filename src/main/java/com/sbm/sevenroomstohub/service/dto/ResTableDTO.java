package com.sbm.sevenroomstohub.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.sbm.sevenroomstohub.domain.ResTable} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResTableDTO implements Serializable {

    private Long id;

    private String tableNumber;

    private ReservationDTO reservation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }

    public ReservationDTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationDTO reservation) {
        this.reservation = reservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResTableDTO)) {
            return false;
        }

        ResTableDTO resTableDTO = (ResTableDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, resTableDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResTableDTO{" +
            "id=" + getId() +
            ", tableNumber=" + getTableNumber() +
            ", reservation=" + getReservation() +
            "}";
    }
}
