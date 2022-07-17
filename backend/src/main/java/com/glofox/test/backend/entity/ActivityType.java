package com.glofox.test.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "activity_type", schema = "public", catalog = "glofox")
public class ActivityType {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @OneToMany(mappedBy = "type")
    @JsonIgnoreProperties("type")
    private Collection<Activity> activities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActivityType that = (ActivityType) o;

        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

    public Collection<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Collection<Activity> activities) {
        this.activities = activities;
    }
}
