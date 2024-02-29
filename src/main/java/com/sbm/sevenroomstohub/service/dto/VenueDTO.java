package com.sbm.sevenroomstohub.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.sbm.sevenroomstohub.domain.Venue} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class VenueDTO implements Serializable {

    private Long id;

    private String address;

    private String blackLogo;

    private String country;

    private String crossStreet;

    private String currencyCode;

    private String externalVenueId;

    private Boolean fullDiningBackend;

    private Boolean gridEnabled;

    private String venueId;

    private String internalName;

    private Boolean membershipEnabled;

    private String name;

    private String neighborhood;

    private String phoneNumber;

    private String policy;

    private String postalCode;

    private String primaryColor;

    private String secondaryColor;

    private String state;

    private String uniqueConfirmationPrefix;

    private String venueClass;

    private String venueGroupId;

    private String venueGroupName;

    private String venueUrlKey;

    private String website;

    private String whiteLogo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBlackLogo() {
        return blackLogo;
    }

    public void setBlackLogo(String blackLogo) {
        this.blackLogo = blackLogo;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getExternalVenueId() {
        return externalVenueId;
    }

    public void setExternalVenueId(String externalVenueId) {
        this.externalVenueId = externalVenueId;
    }

    public Boolean getFullDiningBackend() {
        return fullDiningBackend;
    }

    public void setFullDiningBackend(Boolean fullDiningBackend) {
        this.fullDiningBackend = fullDiningBackend;
    }

    public Boolean getGridEnabled() {
        return gridEnabled;
    }

    public void setGridEnabled(Boolean gridEnabled) {
        this.gridEnabled = gridEnabled;
    }

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getInternalName() {
        return internalName;
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    public Boolean getMembershipEnabled() {
        return membershipEnabled;
    }

    public void setMembershipEnabled(Boolean membershipEnabled) {
        this.membershipEnabled = membershipEnabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPrimaryColor() {
        return primaryColor;
    }

    public void setPrimaryColor(String primaryColor) {
        this.primaryColor = primaryColor;
    }

    public String getSecondaryColor() {
        return secondaryColor;
    }

    public void setSecondaryColor(String secondaryColor) {
        this.secondaryColor = secondaryColor;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUniqueConfirmationPrefix() {
        return uniqueConfirmationPrefix;
    }

    public void setUniqueConfirmationPrefix(String uniqueConfirmationPrefix) {
        this.uniqueConfirmationPrefix = uniqueConfirmationPrefix;
    }

    public String getVenueClass() {
        return venueClass;
    }

    public void setVenueClass(String venueClass) {
        this.venueClass = venueClass;
    }

    public String getVenueGroupId() {
        return venueGroupId;
    }

    public void setVenueGroupId(String venueGroupId) {
        this.venueGroupId = venueGroupId;
    }

    public String getVenueGroupName() {
        return venueGroupName;
    }

    public void setVenueGroupName(String venueGroupName) {
        this.venueGroupName = venueGroupName;
    }

    public String getVenueUrlKey() {
        return venueUrlKey;
    }

    public void setVenueUrlKey(String venueUrlKey) {
        this.venueUrlKey = venueUrlKey;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getWhiteLogo() {
        return whiteLogo;
    }

    public void setWhiteLogo(String whiteLogo) {
        this.whiteLogo = whiteLogo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VenueDTO)) {
            return false;
        }

        VenueDTO venueDTO = (VenueDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, venueDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VenueDTO{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", blackLogo='" + getBlackLogo() + "'" +
            ", country='" + getCountry() + "'" +
            ", crossStreet='" + getCrossStreet() + "'" +
            ", currencyCode='" + getCurrencyCode() + "'" +
            ", externalVenueId='" + getExternalVenueId() + "'" +
            ", fullDiningBackend='" + getFullDiningBackend() + "'" +
            ", gridEnabled='" + getGridEnabled() + "'" +
            ", venueId='" + getVenueId() + "'" +
            ", internalName='" + getInternalName() + "'" +
            ", membershipEnabled='" + getMembershipEnabled() + "'" +
            ", name='" + getName() + "'" +
            ", neighborhood='" + getNeighborhood() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", policy='" + getPolicy() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", primaryColor='" + getPrimaryColor() + "'" +
            ", secondaryColor='" + getSecondaryColor() + "'" +
            ", state='" + getState() + "'" +
            ", uniqueConfirmationPrefix='" + getUniqueConfirmationPrefix() + "'" +
            ", venueClass='" + getVenueClass() + "'" +
            ", venueGroupId='" + getVenueGroupId() + "'" +
            ", venueGroupName='" + getVenueGroupName() + "'" +
            ", venueUrlKey='" + getVenueUrlKey() + "'" +
            ", website='" + getWebsite() + "'" +
            ", whiteLogo='" + getWhiteLogo() + "'" +
            "}";
    }
}
