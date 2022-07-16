package com.glofox.test.backend.dto;

import com.glofox.test.backend.entity.Activity;
import com.glofox.test.backend.entity.ActivityType;
import com.glofox.test.backend.entity.Event;
import com.glofox.test.backend.entity.User;
import com.vladmihalcea.hibernate.type.range.Range;
import org.jetbrains.annotations.NotNull;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.OffsetTime;

@Mapper
public interface ActivityMapper {
    ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

    Activity incoming(ActivityDto activityDto);

    ActivityDto outgoing(Activity activity);

    default String map(@NotNull ActivityType type) {
        return type.getName();
    }

    default ActivityType map(@NotNull String name) {
        ActivityType type = new ActivityType();
        type.setName(name);
        return type;
    }

    default Integer map(@NotNull Event event) {
        return event.getId();
    }

    default String map(@NotNull Range<LocalDate> dateRange) {
        return dateRange.asString();
    }

    default String map(@NotNull OffsetTime time) {
        return time.toString();
    }

    default Integer map(User user) {
        if (user == null) return null;

        return user.getId();
    }

    default User map(Integer id) {
        if (id == null) return null;

        User user = new User();
        user.setId(id);
        return user;
    }

    default Event idToEvent(@NotNull Integer id) {
        Event event = new Event();
        event.setId(id);
        return event;
    }
}
