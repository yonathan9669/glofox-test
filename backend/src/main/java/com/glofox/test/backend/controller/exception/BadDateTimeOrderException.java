package com.glofox.test.backend.controller.exception;

import java.time.OffsetTime;

public class BadDateTimeOrderException extends RuntimeException {

    public BadDateTimeOrderException(OffsetTime start, OffsetTime end) {
        super("End time '(%s)' must be AFTER Start time '(%s)'".formatted(end, start));
    }

    public BadDateTimeOrderException(String dateRange) {
        super("Upper bound must be greater than lower: %s".formatted(dateRange));
    }
}