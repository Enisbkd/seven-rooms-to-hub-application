package com.sbm.sevenroomstohub.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.sbm.sevenroomstohub.domain.ResPosticketsItem} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ResPosticketsItemDTO implements Serializable {

    private Long id;

    private Double price;

    private String name;

    private Integer quantity;

    private ResPosTicketDTO resPosTicket;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ResPosTicketDTO getResPosTicket() {
        return resPosTicket;
    }

    public void setResPosTicket(ResPosTicketDTO resPosTicket) {
        this.resPosTicket = resPosTicket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ResPosticketsItemDTO)) {
            return false;
        }

        ResPosticketsItemDTO resPosticketsItemDTO = (ResPosticketsItemDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, resPosticketsItemDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResPosticketsItemDTO{" +
            "id=" + getId() +
            ", price=" + getPrice() +
            ", name='" + getName() + "'" +
            ", quantity=" + getQuantity() +
            ", resPosTicket=" + getResPosTicket() +
            "}";
    }
}
