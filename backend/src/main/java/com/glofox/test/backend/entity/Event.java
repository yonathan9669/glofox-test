package com.glofox.test.backend.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Collection;

@Entity
public class Event {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "description", nullable = false, length = -1)
    private String description;
    @Basic
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime createdAt;
    @Basic
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime updatedAt;
    @OneToMany(mappedBy = "event")
    private Collection<Activity> activities;
    @ManyToOne
    @JoinColumn(name = "business_id", referencedColumnName = "id", nullable = false)
    private Business business;
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "name", nullable = false)
    private EventType type;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusinessId() {
        return this.business.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return this.type.getName();
    }

    public void setType(EventType eventType) {
        this.type = eventType;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (id != event.id) return false;
        if (this.getBusinessId() != event.getBusinessId()) return false;
        if (name != null ? !name.equals(event.name) : event.name != null) return false;
        if (description != null ? !description.equals(event.description) : event.description != null) return false;

        String type = this.getType();
        String eventType = event.getType();
        if (type != null ? !type.equals(eventType) : eventType != null) return false;

        if (createdAt != null ? !createdAt.equals(event.createdAt) : event.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(event.updatedAt) : event.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + this.getBusinessId();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        String type = this.getType();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }

    public Collection<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Collection<Activity> activities) {
        this.activities = activities;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}
