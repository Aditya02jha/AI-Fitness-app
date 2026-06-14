package com.fitness.activityservice.service;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;

import java.util.List;

public interface ActivityService {
    ActivityResponse getActivityById(String id);
    ActivityResponse createActivity(ActivityRequest request);
    List<ActivityResponse> getActivitiesForUser(String userId);
}
