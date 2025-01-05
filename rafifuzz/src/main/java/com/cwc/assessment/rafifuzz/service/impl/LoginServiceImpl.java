package com.cwc.assessment.rafifuzz.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.cwc.assessment.rafifuzz.dto.request.LoginRequest;
import com.cwc.assessment.rafifuzz.dto.response.LoginResponse;
import com.cwc.assessment.rafifuzz.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	 private final AuthenticationManager authenticationManager;
	 
	 
	public LoginServiceImpl(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}


	@Override
	public LoginResponse login(LoginRequest loginRequest) {
		 try {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
	            );

	            LoginResponse response = new LoginResponse();
	            response.setMessage("Login successful");
	            return response;

	        } catch (BadCredentialsException e) {
	            LoginResponse response = new LoginResponse();
	            response.setMessage("Invalid credentials");
	            return response;
	        }
	}

}
