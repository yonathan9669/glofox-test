package com.glofox.test.backend.controller.exception;

public class DuplicatedBookingException extends RuntimeException {

    public DuplicatedBookingException() {
        super("You can only book once for an activity on the same day");
    }
}