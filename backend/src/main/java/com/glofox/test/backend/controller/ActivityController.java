package com.glofox.test.backend.controller;

import com.glofox.test.backend.dto.ActivityDto;
import com.glofox.test.backend.dto.ActivityMapper;
import com.glofox.test.backend.entity.Activity;
import com.glofox.test.backend.repository.ActivityRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {
    private final ActivityRepository repository;

    public ActivityController(ActivityRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/activities")
    ActivityDto handler(@RequestBody ActivityDto activityDto) {
        Activity activity = ActivityMapper.INSTANCE.incoming(activityDto);

        try {
            repository.save(activity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return ActivityMapper.INSTANCE.outgoing(activity);
    }
}