package com.cwc.assessment.rafifuzz.service;

import com.cwc.assessment.rafifuzz.dto.request.LoginRequest;
import com.cwc.assessment.rafifuzz.dto.response.LoginResponse;

public interface LoginService {
	
	LoginResponse login(LoginRequest loginRequest);
	

}
