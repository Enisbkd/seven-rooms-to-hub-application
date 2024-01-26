package com.sbm.sevenroomstohub.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.sbm.sevenroomstohub.domain.ResCustomField} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResCustomFieldDTO implements Serializable {

    private Long id;

    private String systemName;

    private Integer displayOrder;

    private String name;

    private String value;

    private ReservationDTO reservation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        if (!(o instanceof ResCustomFieldDTO)) {
            return false;
        }

        ResCustomFieldDTO resCustomFieldDTO = (ResCustomFieldDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, resCustomFieldDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResCustomFieldDTO{" +
            "id=" + getId() +
            ", systemName='" + getSystemName() + "'" +
            ", displayOrder=" + getDisplayOrder() +
            ", name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            ", reservation=" + getReservation() +
            "}";
    }
}
