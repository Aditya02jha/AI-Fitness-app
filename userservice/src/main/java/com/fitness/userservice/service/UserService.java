package com.fitness.userservice.service;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;

public interface UserService {
        UserResponse getUserProfile(String userId);
        UserResponse createUser(RegisterRequest registerRequest);
        UserResponse FindUserByEmail(String email);
}
