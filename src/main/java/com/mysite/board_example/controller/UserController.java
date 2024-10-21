package com.mysite.board_example.controller;

import com.mysite.board_example.dto.CreateUserRequest;
import com.mysite.board_example.dto.CreateUserResponse;
import com.mysite.board_example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        CreateUserResponse createUserResponse =  userService.createUser(createUserRequest);
        return new ResponseEntity<>(createUserResponse, HttpStatus.CREATED);
    }
}
