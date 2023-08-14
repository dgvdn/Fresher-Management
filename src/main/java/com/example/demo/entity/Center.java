package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "centers")
public class Center {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String address;
	private String description;
	
	public Center() {
		// TODO Auto-generated constructor stub
	}

	public Center(String name, String address, String description) {
		super();
		
		this.name = name;
		this.address = address;
		this.description = description;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
