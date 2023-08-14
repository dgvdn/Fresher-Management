package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.JWTAuthResponse;
import com.example.demo.dto.LoginDto;
import com.example.demo.service.AuthService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

	private AuthService authService;

	// Build Login REST API
	@PostMapping("/login")
	public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginDto loginDto) {
		String token = authService.login(loginDto);

		JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
		jwtAuthResponse.setAccessToken(token);

		return ResponseEntity.ok(jwtAuthResponse);
	}
}