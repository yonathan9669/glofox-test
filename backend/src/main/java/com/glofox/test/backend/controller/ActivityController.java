package com.glofox.test.backend.controller;

import com.glofox.test.backend.dto.ActivityInput;
import com.glofox.test.backend.dto.ActivityOutput;
import com.glofox.test.backend.entity.Activity;
import com.glofox.test.backend.repository.ActivityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ActivityController {
    private final ActivityRepository repository;
    private final ModelMapper modelMapper;

    public ActivityController(ActivityRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/activities")
    ActivityOutput handler(@RequestBody ActivityInput activityDto) {
        Activity activity;
        try {
            activity = repository.save(modelMapper.map(activityDto, Activity.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return modelMapper.map(activity, ActivityOutput.class);
    }
}