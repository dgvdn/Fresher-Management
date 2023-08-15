package com.example.demo.dto;

public class MarkCalculationRequest {

	private int fresherId;
	private float mark1;
	private float mark2;
	private float mark3;

	// Getters and setters

	public int getFresherId() {
		return fresherId;
	}

	public void setFresherId(int fresherId) {
		this.fresherId = fresherId;
	}

	public float getMark1() {
		return mark1;
	}

	public void setMark1(float mark1) {
		this.mark1 = mark1;
	}

	public float getMark2() {
		return mark2;
	}

	public void setMark2(float mark2) {
		this.mark2 = mark2;
	}

	public float getMark3() {
		return mark3;
	}

	public void setMark3(float mark3) {
		this.mark3 = mark3;
	}
}
