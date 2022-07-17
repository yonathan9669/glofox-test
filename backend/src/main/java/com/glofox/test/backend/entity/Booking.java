package com.glofox.test.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Booking {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Basic
    @Column(name = "attended", nullable = true)
    private Boolean attended;
    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("bookings")
    private User member;
    @ManyToOne
    @JoinColumn(name = "activity_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("bookings")
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getAttended() {
        return attended;
    }

    public void setAttended(Boolean attended) {
        this.attended = attended;
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

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + this.getMemberId();
        result = 31 * result + this.getActivityId();
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (attended != null ? attended.hashCode() : 0);
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
