package com.glofox.test.backend.dto;

import com.vladmihalcea.hibernate.type.range.Range;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.Objects;

public class ActivityDto implements Serializable {
    private final String name;
    private final int capacity;
    private final String description;
    private final Range<LocalDate> dateRange;
    private final OffsetTime startAt;
    private final OffsetTime endAt;
    private final String type;
    private final Integer event;
    private final Integer responsible;
    private Integer id;

    public ActivityDto(String type, String name, int capacity, String description,
                       String dateRange, String startAt, String endAt,
                       Integer event, Integer responsible) {
        this.event = event;
        this.type = type;
        this.responsible = responsible;

        this.name = name;
        this.capacity = capacity;
        this.description = description;

        this.dateRange = Range.localDateRange(dateRange);
        this.startAt = OffsetTime.parse(startAt);
        this.endAt = OffsetTime.parse(endAt);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEvent() {
        return event;
    }

    public Integer getResponsible() {
        return responsible;
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

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityDto entity = (ActivityDto) o;
        return Objects.equals(this.name, entity.name) &&
                Objects.equals(this.capacity, entity.capacity) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.dateRange, entity.dateRange) &&
                Objects.equals(this.startAt, entity.startAt) &&
                Objects.equals(this.endAt, entity.endAt) &&
                Objects.equals(this.event, entity.event) &&
                Objects.equals(this.responsible, entity.responsible) &&
                Objects.equals(this.type, entity.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, capacity, description, dateRange, startAt, endAt, type, responsible, event);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "event = " + event + ", " +
                "name = " + name + ", " +
                "capacity = " + capacity + ", " +
                "description = " + description + ", " +
                "dateRange = " + dateRange + ", " +
                "startAt = " + startAt + ", " +
                "endAt = " + endAt + ", " +
                "type = " + type + ", " +
                "responsible = " + responsible + ")";
    }
}

