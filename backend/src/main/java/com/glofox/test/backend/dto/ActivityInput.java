package com.glofox.test.backend.dto;

import com.vladmihalcea.hibernate.type.range.Range;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.Objects;

public class ActivityInput implements Serializable {
    private final String name;
    private final int capacity;
    private final String description;
    private final Range<LocalDate> dateRange;
    private final OffsetTime startAt;
    private final OffsetTime endAt;
    private final String activityType;
    private final Integer eventId;
    private final Integer responsibleId;

    public ActivityInput(String type, String name, int capacity, String description,
                         String dateRange, String startAt, String endAt,
                         Integer event, Integer responsible) {
        this.eventId = event;
        this.activityType = type;
        this.responsibleId = responsible;

        this.name = name;
        this.capacity = capacity;
        this.description = description;

        this.dateRange = Range.localDateRange(dateRange);
        this.startAt = OffsetTime.parse(startAt);
        this.endAt = OffsetTime.parse(endAt);
    }

    public Integer getEventId() {
        return eventId;
    }

    public Integer getResponsibleId() {
        return responsibleId;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    public Range<LocalDate> getDateRange() {
        return dateRange;
    }

    public OffsetTime getStartAt() {
        return startAt;
    }

    public OffsetTime getEndAt() {
        return endAt;
    }

    public String getActivityType() {
        return activityType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityInput entity = (ActivityInput) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.capacity, entity.capacity) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.dateRange, entity.dateRange) &&
                Objects.equals(this.startAt, entity.startAt) &&
                Objects.equals(this.endAt, entity.endAt) &&
                Objects.equals(this.eventId, entity.eventId) &&
                Objects.equals(this.responsibleId, entity.responsibleId) &&
                Objects.equals(this.activityType, entity.activityType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity, description, dateRange, startAt, endAt, activityType, responsibleId, eventId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "event = " + eventId + ", " +
                "name = " + name + ", " +
                "capacity = " + capacity + ", " +
                "description = " + description + ", " +
                "dateRange = " + dateRange + ", " +
                "startAt = " + startAt + ", " +
                "endAt = " + endAt + ", " +
                "type = " + activityType + ", " +
                "responsible = " + responsibleId + ")";
    }
}

