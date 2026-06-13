package com.fitness.userservice.controller;

import com.fitness.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> createUser(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(userService.createUser(registerRequest));
    }

    //validate user by id
    @GetMapping("/validate/{userId}")
    public ResponseEntity<UserResponse> validateUser(@PathVariable String userId){
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }
}
