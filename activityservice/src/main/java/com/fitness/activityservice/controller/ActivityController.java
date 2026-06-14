package com.fitness.activityservice.controller;

import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.service.ActivityService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
@AllArgsConstructor
public class ActivityController {

    private final ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> createActivity(@RequestBody ActivityRequest request , @RequestHeader ("X-User-Id" )String userId ){
        //validation
        if(userId == null || userId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(activityService.createActivity(request));
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getActivities(@RequestHeader ("X-User-Id" )String userId) {
        //validation
        if(userId == null || userId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(activityService.getActivitiesForUser(userId));
    }

    @GetMapping("/{activityId}")
    public ResponseEntity<ActivityResponse> getActivityById(@RequestHeader ("X-User-Id" )String userId , @PathVariable String activityId) {
        //validation
        if(userId == null || userId.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(activityService.getActivityById(activityId));
    }

}
