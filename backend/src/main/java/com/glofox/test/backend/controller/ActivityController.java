package com.glofox.test.backend.controller;

import com.glofox.test.backend.dto.ActivityDto;
import com.glofox.test.backend.dto.ActivityMapper;
import com.glofox.test.backend.entity.Activity;
import com.glofox.test.backend.service.ActivityService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {
    private final ActivityService service;

    public ActivityController(ActivityService service) {
        this.service = service;
    }

    @PostMapping("/activities")
    ActivityDto.Out handler(@RequestBody ActivityDto activityDto) {
        Activity activity = ActivityMapper.INSTANCE.incoming(activityDto);
        service.save(activity);
        return ActivityMapper.INSTANCE.outgoing(activity);
    }
}