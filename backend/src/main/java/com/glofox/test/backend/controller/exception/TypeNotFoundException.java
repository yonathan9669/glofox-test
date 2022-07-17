package com.glofox.test.backend.controller.exception;

public class TypeNotFoundException extends RuntimeException {

    public TypeNotFoundException(String name) {
        super("Activity type with name: (%s) not found".formatted(name));
    }
}