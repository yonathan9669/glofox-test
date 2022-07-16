package com.glofox.test.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
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
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("businesses")
    private User owner;
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "name", nullable = false)
    @JsonIgnoreProperties("businesses")
    private BusinessType type;
    @OneToMany(mappedBy = "business")
    @JsonIgnoreProperties("business")
    private Collection<Event> events;
    @OneToMany(mappedBy = "business")
    @JsonIgnoreProperties("business")
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

    public void setType(BusinessType businessType) {
        this.type = businessType;
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
        return result;
    }

    @Override
    public String toString() {
        return name + " | " + getType();
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User user) {
        this.owner = user;
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
