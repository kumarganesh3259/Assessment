package com.cwc.assessment.rafifuzz.service.impl;

import com.cwc.assessment.rafifuzz.dto.request.UserRequest;
import com.cwc.assessment.rafifuzz.dto.response.UserResponse;
import com.cwc.assessment.rafifuzz.exceptions.concrete.UserNotFoundException;
import com.cwc.assessment.rafifuzz.models.User;
import com.cwc.assessment.rafifuzz.repository.UserRepository;
import com.cwc.assessment.rafifuzz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerUser(UserRequest userRequest) {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists!");
        }
        userRequest.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
        User user = mapToUserRequest(userRequest);
        return this.userRepository.save(user);

    }

    @Override
    public List<UserResponse> getUserList() {
        List<User> userList = this.userRepository.findAll();
        return userList.stream().map((this::mapToUserResponse)).toList();
    }

    @Override
    public UserResponse getUserById(long userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with associated id "));
        return mapToUserResponse(user);
    }


    private UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
        		.userId(user.getUserId())
                .userName(user.getUserName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .city(user.getCity())
                .pinCode(user.getPinCode())
                .password(user.getPassword())
                .country(user.getCountry())
                .build();
    }

    private User mapToUserRequest(UserRequest userRequest) {
        return User.builder()
        		.userId(userRequest.getUserId())
                .userName(userRequest.getUserName())
                .email(userRequest.getEmail())
                .phone(userRequest.getPhone())
                .address(userRequest.getAddress())
                .city(userRequest.getCity())
                .pinCode(userRequest.getPinCode())
                .password(userRequest.getPassword())
                .country(userRequest.getCountry())
                .build();
    }
}
