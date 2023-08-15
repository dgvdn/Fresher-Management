package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exception.InvalidMarkRangeException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
	public ResponseEntity<String> handleAuthenticationException(AuthenticationCredentialsNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: " + ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGenericException(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Internal Server Error: " + ex.getMessage());
	}

	@ExceptionHandler(InvalidMarkRangeException.class)
	public ResponseEntity<String> handleInvalidMarkRangeException(InvalidMarkRangeException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid mark range: " + ex.getMessage());
	}

}
