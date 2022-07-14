package com.glofox.test.backend.entity;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.sql.Timestamp;

@Entity
public class Booking {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "date", nullable = false)
    private Timestamp date;
    @Basic
    @Column(name = "attended", nullable = true)
    private Boolean attended;
    @Basic
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime createdAt;
    @Basic
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
    private User member;
    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id", nullable = false)
    private Activity activity;

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

    public int getActivityId() {
        return this.activity.getId();
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
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

        Booking booking = (Booking) o;

        if (id != booking.id) return false;
        if (this.getMemberId() != booking.getMemberId()) return false;
        if (this.getActivityId() != booking.getActivityId()) return false;
        if (date != null ? !date.equals(booking.date) : booking.date != null) return false;
        if (attended != null ? !attended.equals(booking.attended) : booking.attended != null) return false;
        if (createdAt != null ? !createdAt.equals(booking.createdAt) : booking.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(booking.updatedAt) : booking.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + this.getMemberId();
        result = 31 * result + this.getActivityId();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (attended != null ? attended.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }

    public User getMember() {
        return member;
    }

    public void setMember(User user) {
        this.member = user;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
