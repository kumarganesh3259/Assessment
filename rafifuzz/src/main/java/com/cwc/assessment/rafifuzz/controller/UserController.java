package com.cwc.assessment.rafifuzz.controller;

import com.cwc.assessment.rafifuzz.dto.response.UserResponse;
import com.cwc.assessment.rafifuzz.service.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

    private final UserService userService;

   
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @SneakyThrows
    @GetMapping("/userlist")
    public ResponseEntity<List<UserResponse>> listUser() {
        List<UserResponse> userList = this.userService.getUserList();
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    @SneakyThrows
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable("userId") Long userId) {
        UserResponse user = this.userService.getUserById(userId);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
