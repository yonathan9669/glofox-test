package com.glofox.test.backend.repository;


import com.glofox.test.backend.entity.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityTypeRepository extends JpaRepository<ActivityType, String> {
}