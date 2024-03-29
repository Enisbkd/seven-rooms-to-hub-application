package com.sbm.sevenroomstohub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A BookingName.
 */
@Entity
@Table(name = "svr_api_booking_name")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BookingName extends AbstractAuditingEntitySBM<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "bookingNames", "client" }, allowSetters = true)
    private ClientVenueStats clientVenueStats;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BookingName id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public BookingName name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientVenueStats getClientVenueStats() {
        return this.clientVenueStats;
    }

    public void setClientVenueStats(ClientVenueStats clientVenueStats) {
        this.clientVenueStats = clientVenueStats;
    }

    public BookingName clientVenueStats(ClientVenueStats clientVenueStats) {
        this.setClientVenueStats(clientVenueStats);
        return this;
    }

    public BookingName(Long id, String name, ClientVenueStats clientVenueStats) {
        this.id = id;
        this.name = name;
        this.clientVenueStats = clientVenueStats;
    }

    public BookingName(String name) {
        this.name = name;
    }

    public BookingName() {}

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BookingName)) {
            return false;
        }
        return getId() != null && getId().equals(((BookingName) o).getId());
    }

    @Override
    public int hashCode() {
        // see
        // https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BookingName{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                super.toString() +
                "}";
    }
}
