package com.example.demo.entity;

public class TKFresherByCenter {
	private Center center;
	private int countFresher;
	
	public TKFresherByCenter() {
		// TODO Auto-generated constructor stub
	}
	
	public TKFresherByCenter(Center center, int countFresher) {
		super();
		this.center = center;
		this.countFresher = countFresher;
	}

	public Center getCenter() {
		return center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}

	public int getCountFresher() {
		return countFresher;
	}

	public void setCountFresher(int countFresher) {
		this.countFresher = countFresher;
	}
	

	
}
