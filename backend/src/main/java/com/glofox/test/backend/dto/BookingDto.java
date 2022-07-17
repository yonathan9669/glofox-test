package com.glofox.test.backend.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class BookingDto implements Serializable {
    private final Integer id;
    private final Integer member;
    private final Integer activity;
    private final LocalDate date;

    public BookingDto(Integer id, Integer member, Integer activity, LocalDate date) {
        this.id = id;
        this.member = member;
        this.activity = activity;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public Integer getMember() {
        return member;
    }

    public Integer getActivity() {
        return activity;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingDto)) return false;
        BookingDto that = (BookingDto) o;

        return Objects.equals(id, that.id) &&
                member.equals(that.member) &&
                activity.equals(that.activity) &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, member, activity, date);
    }
}
