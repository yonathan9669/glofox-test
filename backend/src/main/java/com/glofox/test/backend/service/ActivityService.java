package com.glofox.test.backend.service;

import com.glofox.test.backend.controller.exception.*;
import com.glofox.test.backend.entity.Activity;
import com.glofox.test.backend.repository.ActivityRepository;
import com.glofox.test.backend.repository.ActivityTypeRepository;
import com.glofox.test.backend.repository.EventRepository;
import com.glofox.test.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetTime;

@Service
public class ActivityService {
    private final ActivityRepository activities;
    private final ActivityTypeRepository types;
    private final EventRepository events;
    private final UserRepository users;

    public ActivityService(ActivityRepository activities, ActivityTypeRepository types,
                           EventRepository events, UserRepository users) {
        this.activities = activities;
        this.types = types;
        this.events = events;
        this.users = users;
    }

    public Activity save(Activity activity) {
        // Capacity | Validation
        int capacity = activity.getCapacity();
        if (capacity < 1) {
            throw new InconsistentCapacityException(capacity);
        }

        // Time frame | Validation
        OffsetTime[] interval = new OffsetTime[]{activity.getStartAt(), activity.getEndAt()};
        if (interval[0].isAfter(interval[1])) {
            throw new BadDateTimeOrderException(interval[0], interval[1]);
        }

        // Activity type | Validation
        String typeName = activity.getType().getName();
        types.findById(typeName).orElseThrow(() -> new TypeNotFoundException(typeName));

        // Event | Validation
        Integer eventId = activity.getEvent().getId();
        events.findById(eventId).orElseThrow(() -> new EventNotFoundException(eventId));

        // Responsible | Validation
        if (activity.getResponsible() != null) {
            Integer userId = activity.getResponsible().getId();
            users.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        }

        try {
            activities.save(activity);
        } catch (RuntimeException e) {
            throw new ActivityNotSavedException(e.getMessage());
        }

        return activity;
    }
}
