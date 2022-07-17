package com.glofox.test.backend.dto;

import com.glofox.test.backend.controller.exception.BadDateTimeOrderException;
import com.glofox.test.backend.controller.exception.ExclusiveFieldsException;
import com.glofox.test.backend.controller.exception.IncorrectTimeFormatException;
import com.glofox.test.backend.entity.Booking;
import com.vladmihalcea.hibernate.type.range.Range;
import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BookingsDto implements Serializable {
    private final Integer member;
    private final Integer activity;
    private final List<LocalDate> dates;
    private final Range<LocalDate> dateRange;
    private List<BookingDto> bookings;

    public BookingsDto(@NotNull Integer member, @NotNull Integer activity, String[] dates, String dateRange) {
        if ((dates != null && dateRange != null) || (dates == null && dateRange == null))
            throw new ExclusiveFieldsException((new String[]{"dates", "dateRange"}));

        this.member = member;
        this.activity = activity;
        this.bookings = null;

        try {
            this.dateRange = dateRange == null ? null : Range.localDateRange(dateRange);
        } catch (RuntimeException e) {
            throw new BadDateTimeOrderException(dateRange);
        }

        try {
            this.dates = dates == null ? null : Arrays.stream(dates).map(LocalDate::parse).toList();
        } catch (Exception e) {
            throw new IncorrectTimeFormatException("dates", Arrays.toString(dates));
        }
    }

    public Integer getMember() {
        return member;
    }

    public Integer getActivity() {
        return activity;
    }

    public List<LocalDate> getDates() {
        return dates;
    }

    public Range<LocalDate> getDateRange() {
        return dateRange;
    }

    public List<BookingDto> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings.stream().map(BookingMapper.INSTANCE::outgoing).toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookingsDto)) return false;
        BookingsDto that = (BookingsDto) o;
        return member.equals(that.member) && activity.equals(that.activity) && dates.equals(that.dates) && dateRange.equals(that.dateRange);
    }

    @Override
    public int hashCode() {
        return Objects.hash(member, activity, dates, dateRange);
    }

    public List<BookingDto> toBookingList() {
        List<LocalDate> dates = this.getDatesList();
        return dates.stream().map((date) -> {
            return new BookingDto(null, member, activity, date);
        }).toList();
    }

    private List<LocalDate> getDatesList() {
        return dates != null ? dates : dateRange.lower().datesUntil(dateRange.upper()).toList();
    }
}
