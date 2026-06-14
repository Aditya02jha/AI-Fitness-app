package com.fitness.activityservice.repo;

import com.fitness.activityservice.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepo extends MongoRepository<Activity , String>{
    Activity findByIdAndUserId(String id, String userId);
    List<Activity> findByUserId(String userId);
}
