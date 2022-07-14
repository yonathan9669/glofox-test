package com.glofox.test.backend.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Collection;

@Entity
public class Business {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "address", nullable = true, length = -1)
    private String address;
    @Basic
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime createdAt;
    @Basic
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    private User owner;
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "name", nullable = false)
    private BusinessType type;
    @OneToMany(mappedBy = "business")
    private Collection<Event> events;
    @OneToMany(mappedBy = "business")
    private Collection<Membership> memberships;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOwnerId() {
        return this.owner.getId();
    }

    public String getType() {
        return type.getName();
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

        Business business = (Business) o;

        if (id != business.id) return false;
        if (this.getOwnerId() != business.getOwnerId()) return false;
        if (name != null ? !name.equals(business.name) : business.name != null) return false;
        if (address != null ? !address.equals(business.address) : business.address != null) return false;

        String type = this.getType();
        String businessType = business.getType();
        if (type != null ? !type.equals(businessType) : businessType != null) return false;

        if (createdAt != null ? !createdAt.equals(business.createdAt) : business.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(business.updatedAt) : business.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + this.getOwnerId();
        String type = this.getType();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
    }

    public void setType(BusinessType businessType) {
        this.type = businessType;
    }

    public Collection<Event> getEvents() {
        return events;
    }

    public void setEvents(Collection<Event> events) {
        this.events = events;
    }

    public Collection<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(Collection<Membership> memberships) {
        this.memberships = memberships;
    }
}
