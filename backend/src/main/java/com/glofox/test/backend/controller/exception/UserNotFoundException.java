package com.glofox.test.backend.controller.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Integer id) {
        super("User with id: (%d) not found".formatted(id));
    }
}