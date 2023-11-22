package com.example.demo.dto;

public class FresherResponse {

	private int id;
	private String name;
	private String centerName;
	private float mark1;
	private float mark2;
	private float mark3;
	private float markAvg;
	private String quarterYear;
	private String comment;

	public FresherResponse() {
		// Default constructor
	}

	// Getters and setters for all fields

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public float getMarkAvg() {
		return markAvg;
	}

	public void setMarkAvg(float markAvg) {
		this.markAvg = markAvg;
	}

	public String getQuarterYear() {
		return quarterYear;
	}

	public void setQuarterYear(String quarterYear) {
		this.quarterYear = quarterYear;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
