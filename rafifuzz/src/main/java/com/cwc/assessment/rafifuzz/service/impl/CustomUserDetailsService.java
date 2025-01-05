package com.cwc.assessment.rafifuzz.service.impl;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cwc.assessment.rafifuzz.models.User;
import com.cwc.assessment.rafifuzz.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    
    private final UserRepository userRepository;
    
    

    public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>()
        );
    }

}
// ye intellj Idea mein nhi chalega sts download kr lena us mein chal jayega
// bataya tha na
// pata nhi error ye error q de raha h mein debug nhi kiya may be ye ide ka koi problem hoga so communitty wale fix kr denge

