package com.glofox.test.backend.controller.exception;

public class ActivityNotFoundException extends RuntimeException {

    public ActivityNotFoundException(Integer id) {
        super("Activity with id: (%d) not found".formatted(id));
    }
}