package com.glofox.test.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "business_type", schema = "public", catalog = "glofox")
public class BusinessType {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @OneToMany(mappedBy = "type")
    @JsonIgnoreProperties("type")
    private Collection<Business> businesses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BusinessType that = (BusinessType) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public Collection<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(Collection<Business> businesses) {
        this.businesses = businesses;
    }
}
