package com.sbm.sevenroomstohub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

public class VenueStats implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "venue_id")
    @JsonProperty("venue_id")
    private String venueId;

    @Column(name = "avg_rating")
    @JsonProperty("avg_rating")
    private Integer avgRating;

    @Column(name = "booked_by_names")
    @JsonProperty("booked_by_names")
    private String bookedByNames;

    @Column(name = "last_visit_date")
    @JsonProperty("last_visit_date")
    private String lastVisitDate;

    @Column(name = "num_ratings")
    @JsonProperty("num_ratings")
    private Integer numRatings;

    @Column(name = "total_cancellations")
    @JsonProperty("total_cancellations")
    private Integer totalCancellations;

    @Column(name = "total_covers")
    @JsonProperty("total_covers")
    private Integer totalCovers;

    @Column(name = "total_no_shows")
    @JsonProperty("total_noshows")
    private Integer totalNoShows;

    @Column(name = "total_spend")
    @JsonProperty("total_spend")
    private Double totalSpend;

    @Column(name = "total_spend_local")
    @JsonProperty("total_spend_local")
    private Double totalSpendLocal;

    @Column(name = "total_spend_localper_cover")
    @JsonProperty("total_spend_local_per_cover")
    private Double totalSpendLocalperCover;

    @Column(name = "total_spend_local_per_visit")
    @JsonProperty("total_spend_local_per_visit")
    private Double totalSpendLocalPerVisit;

    @Column(name = "total_spendper_cover")
    @JsonProperty("total_spend_per_cover")
    private Double totalSpendperCover;

    @Column(name = "total_spend_per_visit")
    @JsonProperty("total_spend_per_visit")
    private Double totalSpendPerVisit;

    @Column(name = "total_visit")
    @JsonProperty("total_visits")
    private Integer totalVisit;

    @Column(name = "venue_marketing_optin")
    @JsonProperty("venue_marketing_optin")
    private Boolean venueMarketingOptin;

    @Column(name = "venue_marketing_optints")
    @JsonProperty("venue_marketing_optints")
    private String venueMarketingOptints;

    @Column(name = "tech_lineage")
    private String techLineage;

    @Column(name = "tech_created_date")
    private ZonedDateTime techCreatedDate;

    @Column(name = "tech_updated_date")
    private ZonedDateTime techUpdatedDate;

    @Column(name = "tech_mapping")
    private String techMapping;

    @Column(name = "tech_comment")
    private String techComment;

    @JsonIgnoreProperties(
        value = { "clientPhoto", "VenueStats", "customFields", "clientTags", "reservations", "memberGroups" },
        allowSetters = true
    )
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "VenueStats")
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public VenueStats id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVenueId() {
        return this.venueId;
    }

    public VenueStats venueId(String venueId) {
        this.setVenueId(venueId);
        return this;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public Integer getAvgRating() {
        return this.avgRating;
    }

    public VenueStats avgRating(Integer avgRating) {
        this.setAvgRating(avgRating);
        return this;
    }

    public void setAvgRating(Integer avgRating) {
        this.avgRating = avgRating;
    }

    public String getBookedByNames() {
        return this.bookedByNames;
    }

    public VenueStats bookedByNames(String bookedByNames) {
        this.setBookedByNames(bookedByNames);
        return this;
    }

    public void setBookedByNames(String bookedByNames) {
        this.bookedByNames = bookedByNames;
    }

    public String getLastVisitDate() {
        return this.lastVisitDate;
    }

    public VenueStats lastVisitDate(String lastVisitDate) {
        this.setLastVisitDate(lastVisitDate);
        return this;
    }

    public void setLastVisitDate(String lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public Integer getNumRatings() {
        return this.numRatings;
    }

    public VenueStats numRatings(Integer numRatings) {
        this.setNumRatings(numRatings);
        return this;
    }

    public void setNumRatings(Integer numRatings) {
        this.numRatings = numRatings;
    }

    public Integer getTotalCancellations() {
        return this.totalCancellations;
    }

    public VenueStats totalCancellations(Integer totalCancellations) {
        this.setTotalCancellations(totalCancellations);
        return this;
    }

    public void setTotalCancellations(Integer totalCancellations) {
        this.totalCancellations = totalCancellations;
    }

    public Integer getTotalCovers() {
        return this.totalCovers;
    }

    public VenueStats totalCovers(Integer totalCovers) {
        this.setTotalCovers(totalCovers);
        return this;
    }

    public void setTotalCovers(Integer totalCovers) {
        this.totalCovers = totalCovers;
    }

    public Integer getTotalNoShows() {
        return this.totalNoShows;
    }

    public VenueStats totalNoShows(Integer totalNoShows) {
        this.setTotalNoShows(totalNoShows);
        return this;
    }

    public void setTotalNoShows(Integer totalNoShows) {
        this.totalNoShows = totalNoShows;
    }

    public Double getTotalSpend() {
        return this.totalSpend;
    }

    public VenueStats totalSpend(Double totalSpend) {
        this.setTotalSpend(totalSpend);
        return this;
    }

    public void setTotalSpend(Double totalSpend) {
        this.totalSpend = totalSpend;
    }

    public Double getTotalSpendLocal() {
        return this.totalSpendLocal;
    }

    public VenueStats totalSpendLocal(Double totalSpendLocal) {
        this.setTotalSpendLocal(totalSpendLocal);
        return this;
    }

    public void setTotalSpendLocal(Double totalSpendLocal) {
        this.totalSpendLocal = totalSpendLocal;
    }

    public Double getTotalSpendLocalperCover() {
        return this.totalSpendLocalperCover;
    }

    public VenueStats totalSpendLocalperCover(Double totalSpendLocalperCover) {
        this.setTotalSpendLocalperCover(totalSpendLocalperCover);
        return this;
    }

    public void setTotalSpendLocalperCover(Double totalSpendLocalperCover) {
        this.totalSpendLocalperCover = totalSpendLocalperCover;
    }

    public Double getTotalSpendLocalPerVisit() {
        return this.totalSpendLocalPerVisit;
    }

    public VenueStats totalSpendLocalPerVisit(Double totalSpendLocalPerVisit) {
        this.setTotalSpendLocalPerVisit(totalSpendLocalPerVisit);
        return this;
    }

    public void setTotalSpendLocalPerVisit(Double totalSpendLocalPerVisit) {
        this.totalSpendLocalPerVisit = totalSpendLocalPerVisit;
    }

    public Double getTotalSpendperCover() {
        return this.totalSpendperCover;
    }

    public VenueStats totalSpendperCover(Double totalSpendperCover) {
        this.setTotalSpendperCover(totalSpendperCover);
        return this;
    }

    public void setTotalSpendperCover(Double totalSpendperCover) {
        this.totalSpendperCover = totalSpendperCover;
    }

    public Double getTotalSpendPerVisit() {
        return this.totalSpendPerVisit;
    }

    public VenueStats totalSpendPerVisit(Double totalSpendPerVisit) {
        this.setTotalSpendPerVisit(totalSpendPerVisit);
        return this;
    }

    public void setTotalSpendPerVisit(Double totalSpendPerVisit) {
        this.totalSpendPerVisit = totalSpendPerVisit;
    }

    public Integer getTotalVisit() {
        return this.totalVisit;
    }

    public VenueStats totalVisit(Integer totalVisit) {
        this.setTotalVisit(totalVisit);
        return this;
    }

    public void setTotalVisit(Integer totalVisit) {
        this.totalVisit = totalVisit;
    }

    public Boolean getVenueMarketingOptin() {
        return this.venueMarketingOptin;
    }

    public VenueStats venueMarketingOptin(Boolean venueMarketingOptin) {
        this.setVenueMarketingOptin(venueMarketingOptin);
        return this;
    }

    public void setVenueMarketingOptin(Boolean venueMarketingOptin) {
        this.venueMarketingOptin = venueMarketingOptin;
    }

    public String getVenueMarketingOptints() {
        return this.venueMarketingOptints;
    }

    public VenueStats venueMarketingOptints(String venueMarketingOptints) {
        this.setVenueMarketingOptints(venueMarketingOptints);
        return this;
    }

    public void setVenueMarketingOptints(String venueMarketingOptints) {
        this.venueMarketingOptints = venueMarketingOptints;
    }

    public String getTechLineage() {
        return this.techLineage;
    }

    public VenueStats techLineage(String techLineage) {
        this.setTechLineage(techLineage);
        return this;
    }

    public void setTechLineage(String techLineage) {
        this.techLineage = techLineage;
    }

    public ZonedDateTime getTechCreatedDate() {
        return this.techCreatedDate;
    }

    public VenueStats techCreatedDate(ZonedDateTime techCreatedDate) {
        this.setTechCreatedDate(techCreatedDate);
        return this;
    }

    public void setTechCreatedDate(ZonedDateTime techCreatedDate) {
        this.techCreatedDate = techCreatedDate;
    }

    public ZonedDateTime getTechUpdatedDate() {
        return this.techUpdatedDate;
    }

    public VenueStats techUpdatedDate(ZonedDateTime techUpdatedDate) {
        this.setTechUpdatedDate(techUpdatedDate);
        return this;
    }

    public void setTechUpdatedDate(ZonedDateTime techUpdatedDate) {
        this.techUpdatedDate = techUpdatedDate;
    }

    public String getTechMapping() {
        return this.techMapping;
    }

    public VenueStats techMapping(String techMapping) {
        this.setTechMapping(techMapping);
        return this;
    }

    public void setTechMapping(String techMapping) {
        this.techMapping = techMapping;
    }

    public String getTechComment() {
        return this.techComment;
    }

    public VenueStats techComment(String techComment) {
        this.setTechComment(techComment);
        return this;
    }

    public void setTechComment(String techComment) {
        this.techComment = techComment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VenueStats)) {
            return false;
        }
        return getId() != null && getId().equals(((VenueStats) o).getId());
    }

    public VenueStats() {}

    public VenueStats(
        Long id,
        String venueId,
        Integer avgRating,
        String bookedByNames,
        String lastVisitDate,
        Integer numRatings,
        Integer totalCancellations,
        Integer totalCovers,
        Integer totalNoShows,
        Double totalSpend,
        Double totalSpendLocal,
        Double totalSpendLocalperCover,
        Double totalSpendLocalPerVisit,
        Double totalSpendperCover,
        Double totalSpendPerVisit,
        Integer totalVisit,
        Boolean venueMarketingOptin,
        String venueMarketingOptints,
        String techLineage,
        ZonedDateTime techCreatedDate,
        ZonedDateTime techUpdatedDate,
        String techMapping,
        String techComment,
        Client client
    ) {
        this.id = id;
        this.venueId = venueId;
        this.avgRating = avgRating;
        this.bookedByNames = bookedByNames;
        this.lastVisitDate = lastVisitDate;
        this.numRatings = numRatings;
        this.totalCancellations = totalCancellations;
        this.totalCovers = totalCovers;
        this.totalNoShows = totalNoShows;
        this.totalSpend = totalSpend;
        this.totalSpendLocal = totalSpendLocal;
        this.totalSpendLocalperCover = totalSpendLocalperCover;
        this.totalSpendLocalPerVisit = totalSpendLocalPerVisit;
        this.totalSpendperCover = totalSpendperCover;
        this.totalSpendPerVisit = totalSpendPerVisit;
        this.totalVisit = totalVisit;
        this.venueMarketingOptin = venueMarketingOptin;
        this.venueMarketingOptints = venueMarketingOptints;
        this.techLineage = techLineage;
        this.techCreatedDate = techCreatedDate;
        this.techUpdatedDate = techUpdatedDate;
        this.techMapping = techMapping;
        this.techComment = techComment;
        this.client = client;
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VenueStats{" +
            "id=" + getId() +
            ", venueId='" + getVenueId() + "'" +
            ", avgRating=" + getAvgRating() +
            ", bookedByNames='" + getBookedByNames() + "'" +
            ", lastVisitDate='" + getLastVisitDate() + "'" +
            ", numRatings=" + getNumRatings() +
            ", totalCancellations=" + getTotalCancellations() +
            ", totalCovers=" + getTotalCovers() +
            ", totalNoShows=" + getTotalNoShows() +
            ", totalSpend=" + getTotalSpend() +
            ", totalSpendLocal=" + getTotalSpendLocal() +
            ", totalSpendLocalperCover=" + getTotalSpendLocalperCover() +
            ", totalSpendLocalPerVisit=" + getTotalSpendLocalPerVisit() +
            ", totalSpendperCover=" + getTotalSpendperCover() +
            ", totalSpendPerVisit=" + getTotalSpendPerVisit() +
            ", totalVisit=" + getTotalVisit() +
            ", venueMarketingOptin='" + getVenueMarketingOptin() + "'" +
            ", venueMarketingOptints='" + getVenueMarketingOptints() + "'" +
            ", techLineage='" + getTechLineage() + "'" +
            ", techCreatedDate='" + getTechCreatedDate() + "'" +
            ", techUpdatedDate='" + getTechUpdatedDate() + "'" +
            ", techMapping='" + getTechMapping() + "'" +
            ", techComment='" + getTechComment() + "'" +
            "}";
    }
}
