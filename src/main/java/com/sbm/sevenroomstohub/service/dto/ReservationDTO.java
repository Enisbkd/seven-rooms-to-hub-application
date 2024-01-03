package com.sbm.sevenroomstohub.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.sbm.sevenroomstohub.domain.Reservation} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReservationDTO implements Serializable {

    @JsonIgnore
    private Long id;

    @JsonProperty("id")
    private String resvId;

    private String created;

    private String updated;

    private String deleted;

    @JsonProperty("venue_group_client_id")
    private String venueGroupClientId;

    @JsonProperty("venue_group_id")
    private String venueGroupId;

    @JsonProperty("venue_id")
    private String venueId;

    private String date;

    private Integer duration;

    @JsonProperty("check_numbers")
    private String checkNumbers;

    @JsonProperty("shift_category")
    private String shiftCategory;

    @JsonProperty("shift_persistent_id")
    private String shiftPersistentId;

    @JsonProperty("max_guests")
    private Integer maxGuests;

    @JsonProperty("mf_ratio_male")
    private Integer mfratioMale;

    @JsonProperty("mf_ratio_female")
    private Integer mfratioFemale;

    private String status;

    @JsonProperty("status_display")
    private String statusDisplay;

    @JsonProperty("status_simple")
    private String statusSimple;

    @JsonProperty("access_persistent_id")
    private String accessPersistentId;

    @JsonProperty("arrived_guests")
    private Integer arrivedGuests;

    @JsonProperty("is_vip")
    private Boolean isvip;

    @JsonProperty("booked_by")
    private String bookedby;

    @JsonProperty("client_reference_code")
    private String clientReferenceCode;

    @JsonProperty("last_name")
    private String lastname;

    @JsonProperty("first_name")
    private String firstname;

    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    private String address;

    @JsonProperty("address_2")
    private String address2;

    private String city;

    @JsonProperty("postal_code")
    private String postalCode;

    private String state;

    private String country;

    @JsonProperty("loyalty_id")
    private String loyaltyId;

    @JsonProperty("loyalty_rank")
    private Integer loyaltyRank;

    @JsonProperty("loyalty_tier")
    private String loyaltyTier;

    private String notes;

    @JsonProperty("arrival_time")
    private String arrivalTime;

    @JsonProperty("seated_time")
    private String seatedTime;

    @JsonProperty("left_time")
    private String leftTime;

    @JsonProperty("client_requests")
    private String clientRequests;

    private Integer comps;

    @JsonProperty("comps_price_type")
    private String compsPriceType;

    @JsonProperty("cost_option")
    private Integer costOption;

    private String policy;

    @JsonProperty("min_price")
    private Integer minPrice;

    @JsonProperty("prepayment")
    private Double prePayment;

    @JsonProperty("onsite_payment")
    private Double onsitePayment;

    @JsonProperty("total_payment")
    private Integer totalPayment;

    @JsonProperty("paid_by")
    private String paidBy;

    @JsonProperty("served_by")
    private String servedBy;

    private Integer rating;

    private String problems;

    @JsonProperty("auto_assignments")
    private String autoAssignments;

    @JsonProperty("external_client_id")
    private String externalClientId;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("external_reference_code")
    private String externalReferenceCode;

    @JsonProperty("external_user_id")
    private String externalUserId;

    @JsonProperty("modify_reservation_link")
    private String modifyReservationLink;

    @JsonProperty("reference_code")
    private String referenceCode;

    @JsonProperty("reservation_sms_opt_in")
    private Boolean reservationSmsOptin;

    @JsonProperty("reservation_type")
    private String reservationType;

    @JsonProperty("send_reminder_email")
    private Boolean sendReminderEmail;

    @JsonProperty("send_reminder_sms")
    private Boolean sendreminderSms;

    @JsonProperty("source_client_id")
    private String sourceClientId;

    private String userId;

    private String userName;

    private String techLineage;

    private ZonedDateTime techCreatedDate;

    private ZonedDateTime techUpdatedDate;

    private String techMapping;

    private String techComment;

    private ClientDTO client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResvId() {
        return resvId;
    }

    public void setResvId(String resvId) {
        this.resvId = resvId;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getVenueGroupClientId() {
        return venueGroupClientId;
    }

    public void setVenueGroupClientId(String venueGroupClientId) {
        this.venueGroupClientId = venueGroupClientId;
    }

    public String getVenueGroupId() {
        return venueGroupId;
    }

    public void setVenueGroupId(String venueGroupId) {
        this.venueGroupId = venueGroupId;
    }

    public String getVenueId() {
        return venueId;
    }

    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getCheckNumbers() {
        return checkNumbers;
    }

    public void setCheckNumbers(String checkNumbers) {
        this.checkNumbers = checkNumbers;
    }

    public String getShiftCategory() {
        return shiftCategory;
    }

    public void setShiftCategory(String shiftCategory) {
        this.shiftCategory = shiftCategory;
    }

    public String getShiftPersistentId() {
        return shiftPersistentId;
    }

    public void setShiftPersistentId(String shiftPersistentId) {
        this.shiftPersistentId = shiftPersistentId;
    }

    public Integer getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(Integer maxGuests) {
        this.maxGuests = maxGuests;
    }

    public Integer getMfratioMale() {
        return mfratioMale;
    }

    public void setMfratioMale(Integer mfratioMale) {
        this.mfratioMale = mfratioMale;
    }

    public Integer getMfratioFemale() {
        return mfratioFemale;
    }

    public void setMfratioFemale(Integer mfratioFemale) {
        this.mfratioFemale = mfratioFemale;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusDisplay() {
        return statusDisplay;
    }

    public void setStatusDisplay(String statusDisplay) {
        this.statusDisplay = statusDisplay;
    }

    public String getStatusSimple() {
        return statusSimple;
    }

    public void setStatusSimple(String statusSimple) {
        this.statusSimple = statusSimple;
    }

    public String getAccessPersistentId() {
        return accessPersistentId;
    }

    public void setAccessPersistentId(String accessPersistentId) {
        this.accessPersistentId = accessPersistentId;
    }

    public Integer getArrivedGuests() {
        return arrivedGuests;
    }

    public void setArrivedGuests(Integer arrivedGuests) {
        this.arrivedGuests = arrivedGuests;
    }

    public Boolean getIsvip() {
        return isvip;
    }

    public void setIsvip(Boolean isvip) {
        this.isvip = isvip;
    }

    public String getBookedby() {
        return bookedby;
    }

    public void setBookedby(String bookedby) {
        this.bookedby = bookedby;
    }

    public String getClientReferenceCode() {
        return clientReferenceCode;
    }

    public void setClientReferenceCode(String clientReferenceCode) {
        this.clientReferenceCode = clientReferenceCode;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getSeatedTime() {
        return seatedTime;
    }

    public void setSeatedTime(String seatedTime) {
        this.seatedTime = seatedTime;
    }

    public String getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(String leftTime) {
        this.leftTime = leftTime;
    }

    public String getClientRequests() {
        return clientRequests;
    }

    public void setClientRequests(String clientRequests) {
        this.clientRequests = clientRequests;
    }

    public Integer getComps() {
        return comps;
    }

    public void setComps(Integer comps) {
        this.comps = comps;
    }

    public String getCompsPriceType() {
        return compsPriceType;
    }

    public void setCompsPriceType(String compsPriceType) {
        this.compsPriceType = compsPriceType;
    }

    public Integer getCostOption() {
        return costOption;
    }

    public void setCostOption(Integer costOption) {
        this.costOption = costOption;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Double getPrePayment() {
        return prePayment;
    }

    public void setPrePayment(Double prePayment) {
        this.prePayment = prePayment;
    }

    public Double getOnsitePayment() {
        return onsitePayment;
    }

    public void setOnsitePayment(Double onsitePayment) {
        this.onsitePayment = onsitePayment;
    }

    public Integer getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Integer totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public String getServedBy() {
        return servedBy;
    }

    public void setServedBy(String servedBy) {
        this.servedBy = servedBy;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getProblems() {
        return problems;
    }

    public void setProblems(String problems) {
        this.problems = problems;
    }

    public String getAutoAssignments() {
        return autoAssignments;
    }

    public void setAutoAssignments(String autoAssignments) {
        this.autoAssignments = autoAssignments;
    }

    public String getExternalClientId() {
        return externalClientId;
    }

    public void setExternalClientId(String externalClientId) {
        this.externalClientId = externalClientId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getExternalReferenceCode() {
        return externalReferenceCode;
    }

    public void setExternalReferenceCode(String externalReferenceCode) {
        this.externalReferenceCode = externalReferenceCode;
    }

    public String getExternalUserId() {
        return externalUserId;
    }

    public void setExternalUserId(String externalUserId) {
        this.externalUserId = externalUserId;
    }

    public String getModifyReservationLink() {
        return modifyReservationLink;
    }

    public void setModifyReservationLink(String modifyReservationLink) {
        this.modifyReservationLink = modifyReservationLink;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public Boolean getReservationSmsOptin() {
        return reservationSmsOptin;
    }

    public void setReservationSmsOptin(Boolean reservationSmsOptin) {
        this.reservationSmsOptin = reservationSmsOptin;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    public Boolean getSendReminderEmail() {
        return sendReminderEmail;
    }

    public void setSendReminderEmail(Boolean sendReminderEmail) {
        this.sendReminderEmail = sendReminderEmail;
    }

    public Boolean getSendreminderSms() {
        return sendreminderSms;
    }

    public void setSendreminderSms(Boolean sendreminderSms) {
        this.sendreminderSms = sendreminderSms;
    }

    public String getSourceClientId() {
        return sourceClientId;
    }

    public void setSourceClientId(String sourceClientId) {
        this.sourceClientId = sourceClientId;
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

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ReservationDTO)) {
            return false;
        }

        ReservationDTO reservationDTO = (ReservationDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, reservationDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ReservationDTO{" +
            "id=" + getId() +
            ", resvId='" + getResvId() + "'" +
            ", created='" + getCreated() + "'" +
            ", updated='" + getUpdated() + "'" +
            ", deleted='" + getDeleted() + "'" +
            ", venueGroupClientId='" + getVenueGroupClientId() + "'" +
            ", venueGroupId='" + getVenueGroupId() + "'" +
            ", venueId='" + getVenueId() + "'" +
            ", date='" + getDate() + "'" +
            ", duration=" + getDuration() +
            ", checkNumbers='" + getCheckNumbers() + "'" +
            ", shiftCategory='" + getShiftCategory() + "'" +
            ", shiftPersistentId='" + getShiftPersistentId() + "'" +
            ", maxGuests=" + getMaxGuests() +
            ", mfratioMale=" + getMfratioMale() +
            ", mfratioFemale=" + getMfratioFemale() +
            ", status='" + getStatus() + "'" +
            ", statusDisplay='" + getStatusDisplay() + "'" +
            ", statusSimple='" + getStatusSimple() + "'" +
            ", accessPersistentId='" + getAccessPersistentId() + "'" +
            ", arrivedGuests=" + getArrivedGuests() +
            ", isvip='" + getIsvip() + "'" +
            ", bookedby='" + getBookedby() + "'" +
            ", clientReferenceCode='" + getClientReferenceCode() + "'" +
            ", lastname='" + getLastname() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", address='" + getAddress() + "'" +
            ", address2='" + getAddress2() + "'" +
            ", city='" + getCity() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", state='" + getState() + "'" +
            ", country='" + getCountry() + "'" +
            ", loyaltyId='" + getLoyaltyId() + "'" +
            ", loyaltyRank=" + getLoyaltyRank() +
            ", loyaltyTier='" + getLoyaltyTier() + "'" +
            ", notes='" + getNotes() + "'" +
            ", arrivalTime='" + getArrivalTime() + "'" +
            ", seatedTime='" + getSeatedTime() + "'" +
            ", leftTime='" + getLeftTime() + "'" +
            ", clientRequests='" + getClientRequests() + "'" +
            ", comps=" + getComps() +
            ", compsPriceType='" + getCompsPriceType() + "'" +
            ", costOption=" + getCostOption() +
            ", policy='" + getPolicy() + "'" +
            ", minPrice=" + getMinPrice() +
            ", prePayment=" + getPrePayment() +
            ", onsitePayment=" + getOnsitePayment() +
            ", totalPayment=" + getTotalPayment() +
            ", paidBy='" + getPaidBy() + "'" +
            ", servedBy='" + getServedBy() + "'" +
            ", rating=" + getRating() +
            ", problems='" + getProblems() + "'" +
            ", autoAssignments='" + getAutoAssignments() + "'" +
            ", externalClientId='" + getExternalClientId() + "'" +
            ", externalId='" + getExternalId() + "'" +
            ", externalReferenceCode='" + getExternalReferenceCode() + "'" +
            ", externalUserId='" + getExternalUserId() + "'" +
            ", modifyReservationLink='" + getModifyReservationLink() + "'" +
            ", referenceCode='" + getReferenceCode() + "'" +
            ", reservationSmsOptin='" + getReservationSmsOptin() + "'" +
            ", reservationType='" + getReservationType() + "'" +
            ", sendReminderEmail='" + getSendReminderEmail() + "'" +
            ", sendreminderSms='" + getSendreminderSms() + "'" +
            ", sourceClientId='" + getSourceClientId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", userName='" + getUserName() + "'" +
            ", techLineage='" + getTechLineage() + "'" +
            ", techCreatedDate='" + getTechCreatedDate() + "'" +
            ", techUpdatedDate='" + getTechUpdatedDate() + "'" +
            ", techMapping='" + getTechMapping() + "'" +
            ", techComment='" + getTechComment() + "'" +
            ", client=" + getClient() +
            "}";
    }
}
