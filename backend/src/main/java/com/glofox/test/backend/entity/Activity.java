package com.glofox.test.backend.entity;

import com.vladmihalcea.hibernate.type.range.PostgreSQLRangeType;
import com.vladmihalcea.hibernate.type.range.Range;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.Collection;

@Entity
@TypeDef(
        typeClass = PostgreSQLRangeType.class,
        defaultForType = Range.class
)
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
    private LocalTime startAt;
    @Basic
    @Column(name = "end_at", nullable = false, columnDefinition = "TIME WITH TIME ZONE")
    private LocalTime endAt;
    @Basic
    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime createdAt;
    @Basic
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP WITH TIME ZONE")
    private ZonedDateTime updatedAt;
    @ManyToOne
    @JoinColumn(name = "event_id", referencedColumnName = "id", nullable = false)
    private Event event;
    @ManyToOne
    @JoinColumn(name = "responsible_id", referencedColumnName = "id")
    private User responsible;
    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "name", nullable = false)
    private ActivityType type;
    @OneToMany(mappedBy = "activity")
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

    public LocalTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalTime startAt) {
        this.startAt = startAt;
    }

    public LocalTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalTime endAt) {
        this.endAt = endAt;
    }

    public Integer getResponsibleId() {
        return this.responsible.getId();
    }

    public String getType() {
        return this.type.getName();
    }

    public void setType(ActivityType activityType) {
        this.type = activityType;
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

        if (createdAt != null ? !createdAt.equals(activity.createdAt) : activity.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(activity.updatedAt) : activity.updatedAt != null) return false;

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
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
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
