package com.glofox.test.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class Membership {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "active", nullable = false)
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("memberships")
    private User member;
    @ManyToOne
    @JoinColumn(name = "business_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("memberships")
    private Business business;

    public Boolean getActive() {
        return active;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return this.member.getId();
    }

    public int getBusinessId() {
        return this.business.getId();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Membership that = (Membership) o;

        if (id != that.id) return false;
        if (this.getMemberId() != that.getMemberId()) return false;
        if (this.getBusinessId() != that.getBusinessId()) return false;
        if (active != that.active) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + this.getMemberId();
        result = 31 * result + this.getBusinessId();
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    public User getMember() {
        return member;
    }

    public void setMember(User user) {
        this.member = user;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}
