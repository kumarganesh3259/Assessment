package com.cwc.assessment.rafifuzz.controller;

import com.cwc.assessment.rafifuzz.dto.request.LoginRequest;
import com.cwc.assessment.rafifuzz.dto.request.UserRequest;
import com.cwc.assessment.rafifuzz.dto.response.LoginResponse;
import com.cwc.assessment.rafifuzz.models.User;
import com.cwc.assessment.rafifuzz.service.LoginService;
import com.cwc.assessment.rafifuzz.service.UserService;

import lombok.SneakyThrows;

import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

    private final LoginService loginService;
    private final UserService userService;
   

    public LoginController(UserService userService,LoginService loginService) {
		this.userService = userService;
		this.loginService = loginService;
    }
    
    
    @SneakyThrows
    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody UserRequest userRequest) {
        User registeredUser = userService.registerUser(userRequest);
        return new  ResponseEntity<>(registeredUser, HttpStatus.OK);
    }
    

    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {    	
    	LoginResponse loginResponse = this.loginService.login(loginRequest);
    	return new ResponseEntity<>(loginResponse,HttpStatus.OK);
    }
}

