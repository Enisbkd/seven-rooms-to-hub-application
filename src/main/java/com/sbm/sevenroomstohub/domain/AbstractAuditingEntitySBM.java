package com.sbm.sevenroomstohub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Base abstract class for entities which will hold definitions for created,
 * last modified, created by,
 * last modified by attributes.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate" }, allowGetters = true)
@Getter
@Setter
public abstract class AbstractAuditingEntitySBM<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public abstract T getId();

    @Column(name = "tech_lineage", nullable = false, updatable = false)
    private String tech_lineage = "KAFKA_API_7R";

    @CreationTimestamp
    @Column(name = "tech_created_date", updatable = false)
    private Timestamp techCreatedDate;

    @UpdateTimestamp
    @Column(name = "tech_updated_date", length = 50)
    private Timestamp techUpdatedDate;

    @Column(name = "tech_mapping")
    private Instant techMapping;

    @Column(name = "tech_comment")
    private String techComment;

    @Override
    public String toString() {
        return (
            "AbstractAuditingEntitySBM [tech_lineage=" +
            tech_lineage +
            ", techCreatedDate=" +
            techCreatedDate +
            ", techUpdatedDate=" +
            techUpdatedDate +
            ", techMapping=" +
            techMapping +
            ", techComment=" +
            techComment +
            "]"
        );
    }
}
