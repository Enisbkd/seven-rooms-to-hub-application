package com.sbm.sevenroomstohub.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A ClientTag.
 */
@Entity
@Table(name = "svr_api_client_tag")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ClientTag extends AbstractAuditingEntitySBM<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "tag")
    private String tag;

    @Column(name = "tag_display")
    @JsonProperty("tag_display")
    private String tagDisplay;

    @Column(name = "tag_group")
    private String group;

    @Column(name = "tag_group_display")
    @JsonProperty("tag_group_display")
    private String groupDisplay;

    @Column(name = "color")
    private String color;

    @Column(name = "tag_search_query")
    @JsonProperty("tag_search_query")
    private String tagSearchQuery;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnoreProperties(value = { "clientPhoto", "clientVenueStats", "customFields", "clientTags", "memberGroups" }, allowSetters = true)
    private Client client;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ClientTag id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTag() {
        return this.tag;
    }

    public ClientTag tag(String tag) {
        this.setTag(tag);
        return this;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTagDisplay() {
        return this.tagDisplay;
    }

    public ClientTag tagDisplay(String tagDisplay) {
        this.setTagDisplay(tagDisplay);
        return this;
    }

    public void setTagDisplay(String tagDisplay) {
        this.tagDisplay = tagDisplay;
    }

    public String getGroup() {
        return this.group;
    }

    public ClientTag group(String group) {
        this.setGroup(group);
        return this;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGroupDisplay() {
        return this.groupDisplay;
    }

    public ClientTag groupDisplay(String groupDisplay) {
        this.setGroupDisplay(groupDisplay);
        return this;
    }

    public void setGroupDisplay(String groupDisplay) {
        this.groupDisplay = groupDisplay;
    }

    public String getColor() {
        return this.color;
    }

    public ClientTag color(String color) {
        this.setColor(color);
        return this;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTagSearchQuery() {
        return this.tagSearchQuery;
    }

    public ClientTag tagSearchQuery(String tagSearchQuery) {
        this.setTagSearchQuery(tagSearchQuery);
        return this;
    }

    public void setTagSearchQuery(String tagSearchQuery) {
        this.tagSearchQuery = tagSearchQuery;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ClientTag client(Client client) {
        this.setClient(client);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and
    // setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientTag)) {
            return false;
        }
        return getId() != null && getId().equals(((ClientTag) o).getId());
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
        return "ClientTag{" +
                "id=" + getId() +
                ", tag='" + getTag() + "'" +
                ", tagDisplay='" + getTagDisplay() + "'" +
                ", group='" + getGroup() + "'" +
                ", groupDisplay='" + getGroupDisplay() + "'" +
                ", color='" + getColor() + "'" +
                ", tagSearchQuery='" + getTagSearchQuery() + "'" +
                super.toString() +
                "}";
    }
}
