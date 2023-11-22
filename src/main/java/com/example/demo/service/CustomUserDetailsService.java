package com.example.demo.service;

import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not exists by Username or Email"));

		// You can set a basic authority for all users, e.g., "ROLE_USER".
		GrantedAuthority userAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");

		// Create a UserDetails object with a single authority.
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(username, user.getPassword(),
				Collections.singletonList(userAuthority));

		return userDetails;
	}

}
