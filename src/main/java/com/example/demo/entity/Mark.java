package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name="marks")
public class Mark {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private float mark_1;
	private float mark_2;
	private float mark_3;
	private float mark_avg;
	
	@OneToOne
	private Fresher fresher;
	
	public Mark() {
		
	}

	public Mark(float mark_1, float mark_2, float mark_3, float mark_avg, Fresher fresher) {
		super();
		
		this.mark_1 = mark_1;
		this.mark_2 = mark_2;
		this.mark_3 = mark_3;
		this.mark_avg = mark_avg;
		this.fresher = fresher;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getMark_1() {
		return mark_1;
	}

	public void setMark_1(float mark_1) {
		this.mark_1 = mark_1;
	}

	public float getMark_2() {
		return mark_2;
	}

	public void setMark_2(float mark_2) {
		this.mark_2 = mark_2;
	}

	public float getMark_3() {
		return mark_3;
	}

	public void setMark_3(float mark_3) {
		this.mark_3 = mark_3;
	}

	public float getMark_avg() {
		return mark_avg;
	}

	public void setMark_avg(float mark_avg) {
		this.mark_avg = mark_avg;
	}

	public Fresher getFresher() {
		return fresher;
	}

	public void setFresher(Fresher fresher) {
		this.fresher = fresher;
	}
	
	
	


	
	
	
}
