package com.glofox.test.backend.controller.exception;

import java.time.LocalDate;

public class BookingDateUnavailable extends RuntimeException {

    public BookingDateUnavailable(int activity, LocalDate date) {
        super("Can't book for activity (%d) at date: %s".formatted(activity, date));
    }
}