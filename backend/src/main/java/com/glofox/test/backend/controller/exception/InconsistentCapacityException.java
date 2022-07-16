package com.glofox.test.backend.controller.exception;

public class InconsistentCapacityException extends RuntimeException {

    public InconsistentCapacityException(int capacity) {
        super("Capacity (%d) must be >= 1".formatted(capacity));
    }
}