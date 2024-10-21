package com.mysite.board_example.controller;

import com.mysite.board_example.dto.CheckIdDuplicationResponse;
import com.mysite.board_example.dto.CreateUserRequest;
import com.mysite.board_example.dto.CreateUserResponse;
import com.mysite.board_example.dto.DeleteUserResponse;
import com.mysite.board_example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        CreateUserResponse createUserResponse =  userService.createUser(createUserRequest);
        return new ResponseEntity<>(createUserResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/api/users/{user_id}")
    public ResponseEntity<DeleteUserResponse> deleteUser(@PathVariable("user_id") Integer userId) {
        DeleteUserResponse deleteUserResponse = userService.deleteUser(userId);
        return new ResponseEntity<>(deleteUserResponse, HttpStatus.OK);
    }

    @GetMapping("/api/users/id-check")
    public ResponseEntity<CheckIdDuplicationResponse> checkIdDuplication(@RequestParam("id") String checkingId){
        CheckIdDuplicationResponse checkIdDuplicationResponse = userService.checkIdDuplication(checkingId);
        return new ResponseEntity<>(checkIdDuplicationResponse, HttpStatus.OK);
    }
}
