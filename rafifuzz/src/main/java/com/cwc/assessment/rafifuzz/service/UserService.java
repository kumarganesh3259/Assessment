package com.cwc.assessment.rafifuzz.service;

import com.cwc.assessment.rafifuzz.dto.request.UserRequest;
import com.cwc.assessment.rafifuzz.dto.response.UserResponse;
import com.cwc.assessment.rafifuzz.models.User;

import java.util.List;

public interface UserService {

    User registerUser(UserRequest userRequest);
    List<UserResponse> getUserList();
    UserResponse getUserById(long userId);

}
