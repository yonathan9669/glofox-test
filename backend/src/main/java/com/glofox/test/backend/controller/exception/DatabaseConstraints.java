package com.glofox.test.backend.controller.exception;

public enum DatabaseConstraints {
    DUPLICATED_BOOKING("booking_member_id_activity_id_date_key");

    private final String name;

    DatabaseConstraints(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
