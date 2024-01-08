package com.sbm.sevenroomstohub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbm.sevenroomstohub.service.dto.ResPosticketsItemDTO;
import com.sbm.sevenroomstohub.service.dto.ReservationDTO;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResPosTicketPayload implements Serializable {

    private Long id;

    private String status;

    @JsonProperty("admin_fee")
    private Double adminFee;

    private Integer code;

    @JsonProperty("table_no")
    private String tableNo;

    private Double tax;

    @JsonProperty("business_id")
    private Integer businessId;

    @JsonProperty("local_pos_ticket_id")
    private String localPosticketId;

    @JsonProperty("employee_name")
    private String employeeName;

    private Double total;
    private Double subtotal;

    @JsonProperty("start_time")
    private String startTime;

    @JsonProperty("service_charge")
    private Double serviceCharge;

    @JsonProperty("end_time")
    private String endtime;

    private String techLineage;

    private ZonedDateTime techCreatedDate;

    private ZonedDateTime techUpdatedDate;

    private String techMapping;

    private String techComment;

    private ReservationDTO reservation;

    @JsonProperty("items")
    private Set<ResPosticketsItemDTO> resPosticketsItems = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAdminFee() {
        return adminFee;
    }

    public void setAdminFee(Double adminFee) {
        this.adminFee = adminFee;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTableNo() {
        return tableNo;
    }

    public void setTableNo(String tableNo) {
        this.tableNo = tableNo;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getLocalPosticketId() {
        return localPosticketId;
    }

    public void setLocalPosticketId(String localPosticketId) {
        this.localPosticketId = localPosticketId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
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

    public ReservationDTO getReservation() {
        return reservation;
    }

    public void setReservation(ReservationDTO reservation) {
        this.reservation = reservation;
    }

    public Set<ResPosticketsItemDTO> getResPosticketsItems() {
        return resPosticketsItems;
    }

    public void setResPosticketsItems(Set<ResPosticketsItemDTO> resPosticketsItems) {
        this.resPosticketsItems = resPosticketsItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResPosTicketPayload that = (ResPosTicketPayload) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(status, that.status) &&
            Objects.equals(adminFee, that.adminFee) &&
            Objects.equals(code, that.code) &&
            Objects.equals(tableNo, that.tableNo) &&
            Objects.equals(tax, that.tax) &&
            Objects.equals(businessId, that.businessId) &&
            Objects.equals(localPosticketId, that.localPosticketId) &&
            Objects.equals(employeeName, that.employeeName) &&
            Objects.equals(total, that.total) &&
            Objects.equals(subtotal, that.subtotal) &&
            Objects.equals(startTime, that.startTime) &&
            Objects.equals(serviceCharge, that.serviceCharge) &&
            Objects.equals(endtime, that.endtime) &&
            Objects.equals(techLineage, that.techLineage) &&
            Objects.equals(techCreatedDate, that.techCreatedDate) &&
            Objects.equals(techUpdatedDate, that.techUpdatedDate) &&
            Objects.equals(techMapping, that.techMapping) &&
            Objects.equals(techComment, that.techComment) &&
            Objects.equals(reservation, that.reservation) &&
            Objects.equals(resPosticketsItems, that.resPosticketsItems)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ResPosTicketDTO{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", adminFee=" + getAdminFee() +
            ", code=" + getCode() +
            ", tableNo='" + getTableNo() + "'" +
            ", tax=" + getTax() +
            ", businessId=" + getBusinessId() +
            ", localPosticketId='" + getLocalPosticketId() + "'" +
            ", employeeName='" + getEmployeeName() + "'" +
            ", total=" + getTotal() +
            ", subtotal=" + getSubtotal() +
            ", startTime='" + getStartTime() + "'" +
            ", serviceCharge=" + getServiceCharge() +
            ", endtime='" + getEndtime() + "'" +
            ", techLineage='" + getTechLineage() + "'" +
            ", techCreatedDate='" + getTechCreatedDate() + "'" +
            ", techUpdatedDate='" + getTechUpdatedDate() + "'" +
            ", techMapping='" + getTechMapping() + "'" +
            ", techComment='" + getTechComment() + "'" +
            ", items='" + getResPosticketsItems() + "'" +
            ", reservation=" + getReservation() +
            "}";
    }
}
