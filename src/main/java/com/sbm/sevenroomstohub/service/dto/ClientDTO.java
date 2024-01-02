package com.sbm.sevenroomstohub.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.sbm.sevenroomstohub.domain.Client} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDTO implements Serializable {

    @JsonIgnore
    private Long id;

    @JsonProperty("id")
    private String clientId;

    @JsonProperty("created")
    private String createdDate;

    @JsonProperty("updated")
    private String updatedDate;

    @JsonProperty("deleted")
    private String deletedDate;

    @JsonProperty("last_name")
    private String lastname;

    @JsonProperty("first_name")
    private String firstname;

    private String gender;

    private String salutation;

    private String title;

    @JsonProperty("birthday_day")
    private Integer birthdayDay;

    @JsonProperty("birthday_month")
    private Integer birthdayMonth;

    @JsonProperty("birthday_alt_month")
    private Integer birthdayAltMonth;

    @JsonProperty("anniversary_day")
    private Integer anniversaryDay;

    @JsonProperty("anniversary_month")
    private Integer anniversaryMonth;

    private String company;

    private String email;

    @JsonProperty("email_alt")
    private String emailAlt;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("phone_number_locale")
    private String phoneNumberlocale;

    @JsonProperty("phone_number_alt")
    private String phoneNumberalt;

    @JsonProperty("phone_number_alt_locale")
    private String phoneNumberaltlocale;

    private String address;

    @JsonProperty("address_2")
    private String address2;

    private String city;

    @JsonProperty("postal_code")
    private String postalCode;

    private String state;

    private String country;

    @JsonProperty("is_contact_private")
    private Boolean isContactPrivate;

    @JsonProperty("is_one_time_guest")
    private Boolean isOnetimeGuest;

    private String status;

    @JsonProperty("loyalty_id")
    private String loyaltyId;

    @JsonProperty("loyalty_rank")
    private Integer loyaltyRank;

    @JsonProperty("loyalty_tier")
    private String loyaltyTier;

    @JsonProperty("marketing_opt_in")
    private Boolean marketingOptin;

    @JsonProperty("marketing_opt_in_ts")
    private String marketingOptints;

    @JsonProperty("marketing_opt_out_ts")
    private String marketingOptOutts;

    @JsonProperty("has_billing_profile")
    private Boolean hasBillingProfile;

    private String notes;

    @JsonProperty("private_notes")
    private String privateNotes;

    private String tags;

    @JsonProperty("total_visits")
    private Double totalVisits;

    @JsonProperty("total_covers")
    private Double totalCovers;

    @JsonProperty("total_cancellations")
    private Double totalCancellations;

    @JsonProperty("total_noshows")
    private Double totalNoShows;

    @JsonProperty("total_spend")
    private Double totalSpend;

    @JsonProperty("total_spend_per_cover")
    private Double totalSpendPerCover;

    @JsonProperty("total_spend_per_visit")
    private Double totalspendPerVisit;

    @JsonProperty("avg_rating")
    private Double avgRating;

    @JsonProperty("reference_code")
    private String referenceCode;

    @JsonProperty("external_user_id")
    private String externalUserId;

    @JsonProperty("venue_group_id")
    private String venueGroupId;

    @JsonProperty("birthday_alt_day")
    private Integer birthdayAltDay;

    private String userId;

    private String userName;

    @JsonProperty("total_order_count")
    private Integer totalOrderCount;

    @JsonProperty("preferred_language_code")
    private String preferredLanguageCode;

    private String techLineage;

    private ZonedDateTime techCreatedDate;

    private ZonedDateTime techUpdatedDate;

    private String techMapping;

    private String techComment;

    private ClientPhotoDTO clientPhoto;

    private ClientVenueStatsDTO clientVenueStats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(String deletedDate) {
        this.deletedDate = deletedDate;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getBirthdayDay() {
        return birthdayDay;
    }

    public void setBirthdayDay(Integer birthdayDay) {
        this.birthdayDay = birthdayDay;
    }

    public Integer getBirthdayMonth() {
        return birthdayMonth;
    }

    public void setBirthdayMonth(Integer birthdayMonth) {
        this.birthdayMonth = birthdayMonth;
    }

    public Integer getBirthdayAltMonth() {
        return birthdayAltMonth;
    }

    public void setBirthdayAltMonth(Integer birthdayAltMonth) {
        this.birthdayAltMonth = birthdayAltMonth;
    }

    public Integer getAnniversaryDay() {
        return anniversaryDay;
    }

    public void setAnniversaryDay(Integer anniversaryDay) {
        this.anniversaryDay = anniversaryDay;
    }

    public Integer getAnniversaryMonth() {
        return anniversaryMonth;
    }

    public void setAnniversaryMonth(Integer anniversaryMonth) {
        this.anniversaryMonth = anniversaryMonth;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailAlt() {
        return emailAlt;
    }

    public void setEmailAlt(String emailAlt) {
        this.emailAlt = emailAlt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumberlocale() {
        return phoneNumberlocale;
    }

    public void setPhoneNumberlocale(String phoneNumberlocale) {
        this.phoneNumberlocale = phoneNumberlocale;
    }

    public String getPhoneNumberalt() {
        return phoneNumberalt;
    }

    public void setPhoneNumberalt(String phoneNumberalt) {
        this.phoneNumberalt = phoneNumberalt;
    }

    public String getPhoneNumberaltlocale() {
        return phoneNumberaltlocale;
    }

    public void setPhoneNumberaltlocale(String phoneNumberaltlocale) {
        this.phoneNumberaltlocale = phoneNumberaltlocale;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean getIsContactPrivate() {
        return isContactPrivate;
    }

    public void setIsContactPrivate(Boolean isContactPrivate) {
        this.isContactPrivate = isContactPrivate;
    }

    public Boolean getIsOnetimeGuest() {
        return isOnetimeGuest;
    }

    public void setIsOnetimeGuest(Boolean isOnetimeGuest) {
        this.isOnetimeGuest = isOnetimeGuest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoyaltyId() {
        return loyaltyId;
    }

    public void setLoyaltyId(String loyaltyId) {
        this.loyaltyId = loyaltyId;
    }

    public Integer getLoyaltyRank() {
        return loyaltyRank;
    }

    public void setLoyaltyRank(Integer loyaltyRank) {
        this.loyaltyRank = loyaltyRank;
    }

    public String getLoyaltyTier() {
        return loyaltyTier;
    }

    public void setLoyaltyTier(String loyaltyTier) {
        this.loyaltyTier = loyaltyTier;
    }

    public Boolean getMarketingOptin() {
        return marketingOptin;
    }

    public void setMarketingOptin(Boolean marketingOptin) {
        this.marketingOptin = marketingOptin;
    }

    public String getMarketingOptints() {
        return marketingOptints;
    }

    public void setMarketingOptints(String marketingOptints) {
        this.marketingOptints = marketingOptints;
    }

    public String getMarketingOptOutts() {
        return marketingOptOutts;
    }

    public void setMarketingOptOutts(String marketingOptOutts) {
        this.marketingOptOutts = marketingOptOutts;
    }

    public Boolean getHasBillingProfile() {
        return hasBillingProfile;
    }

    public void setHasBillingProfile(Boolean hasBillingProfile) {
        this.hasBillingProfile = hasBillingProfile;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPrivateNotes() {
        return privateNotes;
    }

    public void setPrivateNotes(String privateNotes) {
        this.privateNotes = privateNotes;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Double getTotalVisits() {
        return totalVisits;
    }

    public void setTotalVisits(Double totalVisits) {
        this.totalVisits = totalVisits;
    }

    public Double getTotalCovers() {
        return totalCovers;
    }

    public void setTotalCovers(Double totalCovers) {
        this.totalCovers = totalCovers;
    }

    public Double getTotalCancellations() {
        return totalCancellations;
    }

    public void setTotalCancellations(Double totalCancellations) {
        this.totalCancellations = totalCancellations;
    }

    public Double getTotalNoShows() {
        return totalNoShows;
    }

    public void setTotalNoShows(Double totalNoShows) {
        this.totalNoShows = totalNoShows;
    }

    public Double getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(Double totalSpend) {
        this.totalSpend = totalSpend;
    }

    public Double getTotalSpendPerCover() {
        return totalSpendPerCover;
    }

    public void setTotalSpendPerCover(Double totalSpendPerCover) {
        this.totalSpendPerCover = totalSpendPerCover;
    }

    public Double getTotalspendPerVisit() {
        return totalspendPerVisit;
    }

    public void setTotalspendPerVisit(Double totalspendPerVisit) {
        this.totalspendPerVisit = totalspendPerVisit;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getVenueGroupId() {
        return venueGroupId;
    }

    public void setVenueGroupId(String venueGroupId) {
        this.venueGroupId = venueGroupId;
    }

    public Integer getBirthdayAltDay() {
        return birthdayAltDay;
    }

    public void setBirthdayAltDay(Integer birthdayAltDay) {
        this.birthdayAltDay = birthdayAltDay;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(Integer totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public String getPreferredLanguageCode() {
        return preferredLanguageCode;
    }

    public void setPreferredLanguageCode(String preferredLanguageCode) {
        this.preferredLanguageCode = preferredLanguageCode;
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

    public ClientPhotoDTO getClientPhoto() {
        return clientPhoto;
    }

    public void setClientPhoto(ClientPhotoDTO clientPhoto) {
        this.clientPhoto = clientPhoto;
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
        if (!(o instanceof ClientDTO)) {
            return false;
        }

        ClientDTO clientDTO = (ClientDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, clientDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientDTO{" +
            "id=" + getId() +
            ", clientId='" + getClientId() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", updatedDate='" + getUpdatedDate() + "'" +
            ", deletedDate='" + getDeletedDate() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", gender='" + getGender() + "'" +
            ", salutation='" + getSalutation() + "'" +
            ", title='" + getTitle() + "'" +
            ", birthdayDay=" + getBirthdayDay() +
            ", birthdayMonth=" + getBirthdayMonth() +
            ", birthdayAltMonth=" + getBirthdayAltMonth() +
            ", anniversaryDay=" + getAnniversaryDay() +
            ", anniversaryMonth=" + getAnniversaryMonth() +
            ", company='" + getCompany() + "'" +
            ", email='" + getEmail() + "'" +
            ", emailAlt='" + getEmailAlt() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", phoneNumberlocale='" + getPhoneNumberlocale() + "'" +
            ", phoneNumberalt='" + getPhoneNumberalt() + "'" +
            ", phoneNumberaltlocale='" + getPhoneNumberaltlocale() + "'" +
            ", address='" + getAddress() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", city='" + getCity() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", state='" + getState() + "'" +
            ", country='" + getCountry() + "'" +
            ", isContactPrivate='" + getIsContactPrivate() + "'" +
            ", isOnetimeGuest='" + getIsOnetimeGuest() + "'" +
            ", status='" + getStatus() + "'" +
            ", loyaltyId='" + getLoyaltyId() + "'" +
            ", loyaltyRank=" + getLoyaltyRank() +
            ", loyaltyTier='" + getLoyaltyTier() + "'" +
            ", marketingOptin='" + getMarketingOptin() + "'" +
            ", marketingOptints='" + getMarketingOptints() + "'" +
            ", marketingOptOutts='" + getMarketingOptOutts() + "'" +
            ", hasBillingProfile='" + getHasBillingProfile() + "'" +
            ", notes='" + getNotes() + "'" +
            ", privateNotes='" + getPrivateNotes() + "'" +
            ", tags='" + getTags() + "'" +
            ", totalVisits=" + getTotalVisits() +
            ", totalCovers=" + getTotalCovers() +
            ", totalCancellations=" + getTotalCancellations() +
            ", totalNoShows=" + getTotalNoShows() +
            ", totalSpend=" + getTotalSpend() +
            ", totalSpendPerCover=" + getTotalSpendPerCover() +
            ", totalspendPerVisit=" + getTotalspendPerVisit() +
            ", avgRating=" + getAvgRating() +
            ", referenceCode='" + getReferenceCode() + "'" +
            ", externalUserId='" + getExternalUserId() + "'" +
            ", venueGroupId='" + getVenueGroupId() + "'" +
            ", birthdayAltDay=" + getBirthdayAltDay() +
            ", userId='" + getUserId() + "'" +
            ", userName='" + getUserName() + "'" +
            ", totalOrderCount=" + getTotalOrderCount() +
            ", preferredLanguageCode='" + getPreferredLanguageCode() + "'" +
            ", techLineage='" + getTechLineage() + "'" +
            ", techCreatedDate='" + getTechCreatedDate() + "'" +
            ", techUpdatedDate='" + getTechUpdatedDate() + "'" +
            ", techMapping='" + getTechMapping() + "'" +
            ", techComment='" + getTechComment() + "'" +
            ", clientPhoto=" + getClientPhoto() +
            ", clientVenueStats=" + getClientVenueStats() +
            "}";
    }
}
