package com.glofox.test.backend.controller.exception;

public class BookingNotSavedException extends RuntimeException {

    public BookingNotSavedException(String message) {
        super("Booking was unexpectedly not saved\n%s".formatted(message));
    }
}