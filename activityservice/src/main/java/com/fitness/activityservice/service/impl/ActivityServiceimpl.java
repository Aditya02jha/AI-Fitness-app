package com.fitness.activityservice.service.impl;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.repo.ActivityRepo;
import com.fitness.activityservice.service.ActivityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityServiceimpl implements ActivityService {

    public final ActivityRepo activityRepo;

    @Override
    public ActivityResponse getActivityById(String id) {
        Activity activity = activityRepo.findById(id).orElseThrow(() -> new RuntimeException("Activity not found with id: " + id));
        log.info("Retrieved activity with id: {}", id);
        return mapToResponse(activity);
    }

    @Override
    public ActivityResponse createActivity(ActivityRequest request) {
            Activity activity = new Activity();
            activity.setUserId(request.getUserId());
            activity.setType(request.getType());
            activity.setDuration(request.getDuration());
            activity.setCaloriesBurned(request.getCaloriesBurned());
            activity.setStartTime(request.getStartTime());
            activity.setAdditionalMetrics(request.getAdditionalMetrics());

            Activity savedActivity = activityRepo.save(activity);
            log.info("Created new activity with id: {}", savedActivity.getId());
            return mapToResponse(savedActivity);
    }

    @Override
    public List<ActivityResponse> getActivitiesForUser(String userId) {
        List<Activity> activities = activityRepo.findByUserId(userId);
        log.info("Retrieved {} activities for user id: {}", activities.size(), userId);
        return activities.stream().map(this::mapToResponse).toList();
    }

    private ActivityResponse mapToResponse(Activity activity){
        ActivityResponse response = new ActivityResponse();
        response.setId(activity.getId());
        response.setUserId(activity.getUserId());
        response.setType(activity.getType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setStartTime(activity.getStartTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
        return response;
    }
}
