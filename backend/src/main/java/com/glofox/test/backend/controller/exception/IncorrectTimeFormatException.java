package com.glofox.test.backend.controller.exception;

public class IncorrectTimeFormatException extends RuntimeException {

    public IncorrectTimeFormatException(String name, String time) {
        super("Incorrect format for %s time (%s)".formatted(name, time));
    }
}
