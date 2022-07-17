package com.glofox.test.backend.controller.exception;

import java.util.Arrays;

public class ExclusiveFieldsException extends RuntimeException {

    public ExclusiveFieldsException(String[] fields) {
        super("The %s fields are exclusive, only one of them at a time is required".formatted(Arrays.toString(fields)));
    }
}