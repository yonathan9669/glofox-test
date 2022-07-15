package com.glofox.test.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.range.PostgreSQLRangeType;
import com.vladmihalcea.hibernate.type.range.Range;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.Collection;

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
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("activities")
    private Event event;
    @ManyToOne
    @JoinColumn(name = "responsible_id", referencedColumnName = "id")
    @JsonIgnoreProperties("activities")
    private User responsible;
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "name", nullable = false)
    @JsonIgnoreProperties("activities")
    private ActivityType type;
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

    public int getEventId() {
        return this.event.getId();
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

    public void setDateRange(String dateRange) {
        this.dateRange = Range.localDateRange(dateRange);
    }

    public OffsetTime getStartAt() {
        return startAt;
    }

    public void setStartAt(OffsetTime startAt) {
        this.startAt = startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = OffsetTime.parse(startAt);
    }

    public OffsetTime getEndAt() {
        return endAt;
    }

    public void setEndAt(OffsetTime endAt) {
        this.endAt = endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = OffsetTime.parse(endAt);
    }

    public Integer getResponsibleId() {
        if (this.responsible == null) return null;
        return this.responsible.getId();
    }

    public String getType() {
        return this.type.getName();
    }

    public void setType(ActivityType activityType) {
        this.type = activityType;
    }

    public void setType(String name) {
        this.type = new ActivityType(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Activity activity = (Activity) o;

        if (id != activity.id) return false;
        if (this.getEventId() != activity.getEventId()) return false;
        if (capacity != activity.capacity) return false;
        if (name != null ? !name.equals(activity.name) : activity.name != null) return false;
        if (description != null ? !description.equals(activity.description) : activity.description != null)
            return false;
        if (dateRange != null ? !dateRange.equals(activity.dateRange) : activity.dateRange != null) return false;
        if (startAt != null ? !startAt.equals(activity.startAt) : activity.startAt != null) return false;
        if (endAt != null ? !endAt.equals(activity.endAt) : activity.endAt != null) return false;

        Integer responsibleId = this.getResponsibleId();
        Integer activityResponsibleId = activity.getResponsibleId();
        if (responsibleId != null ? !responsibleId.equals(activityResponsibleId) : activityResponsibleId != null)
            return false;

        String type = this.getType();
        String activityType = activity.getType();
        if (type != null ? !type.equals(activityType) : activityType != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + this.getEventId();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dateRange != null ? dateRange.hashCode() : 0);
        result = 31 * result + (startAt != null ? startAt.hashCode() : 0);
        result = 31 * result + (endAt != null ? endAt.hashCode() : 0);
        Integer responsibleId = this.getResponsibleId();
        result = 31 * result + (responsibleId != null ? responsibleId.hashCode() : 0);
        String type = this.getType();
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setEvent(int event_id) {
        this.event = new Event(event_id);
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User user) {
        this.responsible = user;
    }

    public void setResponsible(int responsible_id) {
        this.responsible = new User(responsible_id);
    }

    public Collection<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(Collection<Booking> bookings) {
        this.bookings = bookings;
    }
}
