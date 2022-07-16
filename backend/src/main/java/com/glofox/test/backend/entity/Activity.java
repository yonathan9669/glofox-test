package com.glofox.test.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.range.PostgreSQLRangeType;
import com.vladmihalcea.hibernate.type.range.Range;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.Collection;
import java.util.Objects;

@Entity
@TypeDef(typeClass = PostgreSQLRangeType.class, defaultForType = Range.class)
public class Activity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @Basic
    @Column(name = "capacity", nullable = false)
    private int capacity;
    @Basic
    @Column(name = "description", nullable = true, length = -1)
    private String description;
    @Basic
    @Column(name = "date_range", nullable = false, columnDefinition = "DATERANGE")
    private Range<LocalDate> dateRange;
    @Basic
    @Column(name = "start_at", nullable = false, columnDefinition = "TIME WITH TIME ZONE")
    private OffsetTime startAt;
    @Basic
    @Column(name = "end_at", nullable = false, columnDefinition = "TIME WITH TIME ZONE")
    private OffsetTime endAt;
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "name", nullable = false)
    @JsonIgnoreProperties("activities")
    private ActivityType type;
    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("activities")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "responsible_id", referencedColumnName = "id")
    @JsonIgnoreProperties("activities")
    private User responsible;
    @OneToMany(mappedBy = "activity")
    @JsonIgnoreProperties("activity")
    private Collection<Booking> bookings;

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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Range<LocalDate> getDateRange() {
        return dateRange;
    }

    public void setDateRange(Range<LocalDate> dateRange) {
        this.dateRange = dateRange;
    }

    public OffsetTime getStartAt() {
        return startAt;
    }

    public void setStartAt(OffsetTime startAt) {
        this.startAt = startAt;
    }

    public OffsetTime getEndAt() {
        return endAt;
    }

    public void setEndAt(OffsetTime endAt) {
        this.endAt = endAt;
    }

    public ActivityType getType() {
        return this.type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        return Objects.equals(this.id, activity.id) &&
                Objects.equals(this.name, activity.name) &&
                Objects.equals(this.capacity, activity.capacity) &&
                Objects.equals(this.description, activity.description) &&
                Objects.equals(this.dateRange, activity.dateRange) &&
                Objects.equals(this.startAt, activity.startAt) &&
                Objects.equals(this.endAt, activity.endAt) &&
                Objects.equals(this.event, activity.event) &&
                Objects.equals(this.responsible, activity.responsible) &&
                Objects.equals(this.type, activity.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, capacity, description, dateRange, startAt, endAt, type, responsible, event);
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User user) {
        this.responsible = user;
    }

    public Collection<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Collection<Booking> bookings) {
        this.bookings = bookings;
    }
}
