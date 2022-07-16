package com.glofox.test.backend.controller.exception;

public class ActivityNotSavedException extends RuntimeException {

    public ActivityNotSavedException(String message) {
        super("Activity was unexpectedly not saved\n%s".formatted(message));
    }
}