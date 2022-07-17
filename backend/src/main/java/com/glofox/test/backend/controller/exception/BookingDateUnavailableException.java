package com.glofox.test.backend.controller.exception;

import java.time.LocalDate;

public class BookingDateUnavailableException extends RuntimeException {

    public BookingDateUnavailableException(int activity, LocalDate date) {
        super("Can't book for activity (%d) at date: %s".formatted(activity, date));
    }
}