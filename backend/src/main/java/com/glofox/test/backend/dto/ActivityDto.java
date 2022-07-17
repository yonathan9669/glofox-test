package com.glofox.test.backend.dto;

import com.glofox.test.backend.controller.exception.BadDateTimeOrderException;
import com.glofox.test.backend.controller.exception.IncorrectTimeFormatException;
import com.vladmihalcea.hibernate.type.range.Range;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.OffsetTime;
import java.util.Objects;

public class ActivityDto implements Serializable {
    private final String name;
    private final int capacity;
    private final Boolean overbooking;
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
                       Integer event, Integer responsible, Boolean overbooking) {
        this.event = event;
        this.type = type;
        this.responsible = responsible;

        this.name = name;
        this.capacity = capacity;
        this.overbooking = overbooking != null && overbooking;
        this.description = description;

        try {
            this.dateRange = Range.localDateRange(dateRange);
        } catch (RuntimeException e) {
            throw new BadDateTimeOrderException(dateRange);
        }

        try {
            this.startAt = OffsetTime.parse(startAt);
        } catch (RuntimeException e) {
            throw new IncorrectTimeFormatException("startAt", startAt);
        }

        try {
            this.endAt = OffsetTime.parse(endAt);
        } catch (RuntimeException e) {
            throw new IncorrectTimeFormatException("endAt", endAt);
        }
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

    public Boolean getOverbooking() {
        return overbooking;
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
                Objects.equals(this.overbooking, entity.overbooking) &&
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
        return Objects.hash(name, capacity, overbooking, description, dateRange, startAt, endAt, type, responsible, event);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "event = " + event + ", " +
                "name = " + name + ", " +
                "capacity = " + capacity + ", " +
                "overbooking = " + overbooking + ", " +
                "description = " + description + ", " +
                "dateRange = " + dateRange + ", " +
                "startAt = " + startAt + ", " +
                "endAt = " + endAt + ", " +
                "type = " + type + ", " +
                "responsible = " + responsible + ")";
    }

    public static class Out {
        public Integer id;
        public String name;
        public int capacity;
        public boolean overbooking;
        public String description;
        public DateRange dateRange;
        public String startAt;
        public String endAt;
        public String type;
        public Integer event;
        public Integer responsible;
    }

    public static class DateRange {
        public String start;
        public String end;
        public String range;
    }
}

