package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkCalculationResponse {
	private float averageMark;
	private String resultMessage; // You can customize this message as needed

	// Constructors, getters, and setters
}
