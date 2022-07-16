package com.glofox.test.backend.controller.exception;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(Integer id) {
        super("Event with id: (%d) not found".formatted(id));
    }
}