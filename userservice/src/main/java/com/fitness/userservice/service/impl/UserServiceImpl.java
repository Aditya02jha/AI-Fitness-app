package com.fitness.userservice.service.impl;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.model.User;
import com.fitness.userservice.repo.UserRepo;
import com.fitness.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public UserResponse getUserProfile(String userId) {
        User user  = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        return userResponse;

    }

    @Override
    public UserResponse createUser(RegisterRequest registerRequest) {

        UserResponse existingUser = this.FindUserByEmail(registerRequest.getEmail());
        if(existingUser != null) {
            throw new RuntimeException("User Already Exists");
        }
        User RegisteredUser = new User();
        RegisteredUser.setEmail(registerRequest.getEmail());
        RegisteredUser.setFirstName(registerRequest.getFirstName());
        RegisteredUser.setLastName(registerRequest.getLastName());
        RegisteredUser.setPassword(registerRequest.getPassword());

        userRepo.save(RegisteredUser);
        UserResponse userResponse = new UserResponse();
        userResponse.setId(RegisteredUser.getId());
        userResponse.setFirstName(RegisteredUser.getFirstName());
        userResponse.setLastName(RegisteredUser.getLastName());
        userResponse.setEmail(RegisteredUser.getEmail());
        userResponse.setCreatedAt(RegisteredUser.getCreatedAt());
        userResponse.setUpdatedAt(RegisteredUser.getUpdatedAt());
        return userResponse;
    }

    @Override
    public UserResponse FindUserByEmail(String email) {
        User user  = userRepo.findByEmail(email);
        if(user == null){
            throw new RuntimeException("User Not Found");
        }
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setEmail(user.getEmail());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        return userResponse;
    }
}
