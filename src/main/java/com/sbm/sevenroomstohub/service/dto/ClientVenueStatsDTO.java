package com.sbm.sevenroomstohub.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.sbm.sevenroomstohub.domain.ClientVenueStats} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClientVenueStatsDTO implements Serializable {

    private Long id;

    private Double totalSpendLocalperCover;

    private String lastVisitDate;

    private Integer totalCancellations;

    private Integer totalCovers;

    private Integer avgRating;

    private Double totalSpendperCover;

    private Double totalSpend;

    private Integer totalNoShows;

    private Integer numRatings;

    private Double totalSpendPerVisit;

    private Double totalSpendLocal;

    private Double totalSpendLocalPerVisit;

    private Integer totalVisits;

    private Double grossTotal;

    private Double totalOrderCount;

    private Double totalOrderCancellations;

    private Double totalOrderSpend;

    private Double grossOrderTotal;

    private Double totalOrderSpendLocal;

    private String lastOrderDate;

    private Double totalSpendperOrder;

    private Double totalSpendLocalperOrder;

    private String venueId;

    private Boolean venueMarketingOptin;

    private String venueMarketingOptints;

    private String techLineage;

    private ZonedDateTime techCreatedDate;

    private ZonedDateTime techUpdatedDate;

    private String techMapping;

    private String techComment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalSpendLocalperCover() {
        return totalSpendLocalperCover;
    }

    public void setTotalSpendLocalperCover(Double totalSpendLocalperCover) {
        this.totalSpendLocalperCover = totalSpendLocalperCover;
    }

    public String getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(String lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public Integer getTotalCancellations() {
        return totalCancellations;
    }

    public void setTotalCancellations(Integer totalCancellations) {
        this.totalCancellations = totalCancellations;
    }

    public Integer getTotalCovers() {
        return totalCovers;
    }

    public void setTotalCovers(Integer totalCovers) {
        this.totalCovers = totalCovers;
    }

    public Integer getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Integer avgRating) {
        this.avgRating = avgRating;
    }

    public Double getTotalSpendperCover() {
        return totalSpendperCover;
    }

    public void setTotalSpendperCover(Double totalSpendperCover) {
        this.totalSpendperCover = totalSpendperCover;
    }

    public Double getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(Double totalSpend) {
        this.totalSpend = totalSpend;
    }

    public Integer getTotalNoShows() {
        return totalNoShows;
    }

    public void setTotalNoShows(Integer totalNoShows) {
        this.totalNoShows = totalNoShows;
    }

    public Integer getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(Integer numRatings) {
        this.numRatings = numRatings;
    }

    public Double getTotalSpendPerVisit() {
        return totalSpendPerVisit;
    }

    public void setTotalSpendPerVisit(Double totalSpendPerVisit) {
        this.totalSpendPerVisit = totalSpendPerVisit;
    }

    public Double getTotalSpendLocal() {
        return totalSpendLocal;
    }

    public void setTotalSpendLocal(Double totalSpendLocal) {
        this.totalSpendLocal = totalSpendLocal;
    }

    public Double getTotalSpendLocalPerVisit() {
        return totalSpendLocalPerVisit;
    }

    public void setTotalSpendLocalPerVisit(Double totalSpendLocalPerVisit) {
        this.totalSpendLocalPerVisit = totalSpendLocalPerVisit;
    }

    public Integer getTotalVisits() {
        return totalVisits;
    }

    public void setTotalVisits(Integer totalVisits) {
        this.totalVisits = totalVisits;
    }

    public Double getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(Double grossTotal) {
        this.grossTotal = grossTotal;
    }

    public Double getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(Double totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public Double getTotalOrderCancellations() {
        return totalOrderCancellations;
    }

    public void setTotalOrderCancellations(Double totalOrderCancellations) {
        this.totalOrderCancellations = totalOrderCancellations;
    }

    public Double getTotalOrderSpend() {
        return totalOrderSpend;
    }

    public void setTotalOrderSpend(Double totalOrderSpend) {
        this.totalOrderSpend = totalOrderSpend;
    }

    public Double getGrossOrderTotal() {
        return grossOrderTotal;
    }

    public void setGrossOrderTotal(Double grossOrderTotal) {
        this.grossOrderTotal = grossOrderTotal;
    }

    public Double getTotalOrderSpendLocal() {
        return totalOrderSpendLocal;
    }

    public void setTotalOrderSpendLocal(Double totalOrderSpendLocal) {
        this.totalOrderSpendLocal = totalOrderSpendLocal;
    }

    public String getLastOrderDate() {
        return lastOrderDate;
    }

    public void setLastOrderDate(String lastOrderDate) {
        this.lastOrderDate = lastOrderDate;
    }

    public Double getTotalSpendperOrder() {
        return totalSpendperOrder;
    }

    public void setTotalSpendperOrder(Double totalSpendperOrder) {
        this.totalSpendperOrder = totalSpendperOrder;
    }

    public Double getTotalSpendLocalperOrder() {
        return totalSpendLocalperOrder;
    }

    public void setTotalSpendLocalperOrder(Double totalSpendLocalperOrder) {
        this.totalSpendLocalperOrder = totalSpendLocalperOrder;
    }

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public Boolean getVenueMarketingOptin() {
        return venueMarketingOptin;
    }

    public void setVenueMarketingOptin(Boolean venueMarketingOptin) {
        this.venueMarketingOptin = venueMarketingOptin;
    }

    public String getVenueMarketingOptints() {
        return venueMarketingOptints;
    }

    public void setVenueMarketingOptints(String venueMarketingOptints) {
        this.venueMarketingOptints = venueMarketingOptints;
    }

    public String getTechLineage() {
        return techLineage;
    }

    public void setTechLineage(String techLineage) {
        this.techLineage = techLineage;
    }

    public ZonedDateTime getTechCreatedDate() {
        return techCreatedDate;
    }

    public void setTechCreatedDate(ZonedDateTime techCreatedDate) {
        this.techCreatedDate = techCreatedDate;
    }

    public ZonedDateTime getTechUpdatedDate() {
        return techUpdatedDate;
    }

    public void setTechUpdatedDate(ZonedDateTime techUpdatedDate) {
        this.techUpdatedDate = techUpdatedDate;
    }

    public String getTechMapping() {
        return techMapping;
    }

    public void setTechMapping(String techMapping) {
        this.techMapping = techMapping;
    }

    public String getTechComment() {
        return techComment;
    }

    public void setTechComment(String techComment) {
        this.techComment = techComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientVenueStatsDTO)) {
            return false;
        }

        ClientVenueStatsDTO clientVenueStatsDTO = (ClientVenueStatsDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, clientVenueStatsDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientVenueStatsDTO{" +
            "id=" + getId() +
            ", totalSpendLocalperCover=" + getTotalSpendLocalperCover() +
            ", lastVisitDate='" + getLastVisitDate() + "'" +
            ", totalCancellations=" + getTotalCancellations() +
            ", totalCovers=" + getTotalCovers() +
            ", avgRating=" + getAvgRating() +
            ", totalSpendperCover=" + getTotalSpendperCover() +
            ", totalSpend=" + getTotalSpend() +
            ", totalNoShows=" + getTotalNoShows() +
            ", numRatings=" + getNumRatings() +
            ", totalSpendPerVisit=" + getTotalSpendPerVisit() +
            ", totalSpendLocal=" + getTotalSpendLocal() +
            ", totalSpendLocalPerVisit=" + getTotalSpendLocalPerVisit() +
            ", totalVisits=" + getTotalVisits() +
            ", grossTotal=" + getGrossTotal() +
            ", totalOrderCount=" + getTotalOrderCount() +
            ", totalOrderCancellations=" + getTotalOrderCancellations() +
            ", totalOrderSpend=" + getTotalOrderSpend() +
            ", grossOrderTotal=" + getGrossOrderTotal() +
            ", totalOrderSpendLocal=" + getTotalOrderSpendLocal() +
            ", lastOrderDate='" + getLastOrderDate() + "'" +
            ", totalSpendperOrder=" + getTotalSpendperOrder() +
            ", totalSpendLocalperOrder=" + getTotalSpendLocalperOrder() +
            ", venueId='" + getVenueId() + "'" +
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
