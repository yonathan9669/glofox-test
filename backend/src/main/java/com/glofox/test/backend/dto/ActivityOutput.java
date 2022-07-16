package com.glofox.test.backend.dto;

import java.io.Serializable;
import java.util.Objects;

public class ActivityOutput implements Serializable {
    private int id;

    public ActivityOutput() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivityOutput that = (ActivityOutput) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "ActivityOutput{" +
                "id=" + id +
                '}';
    }
}
