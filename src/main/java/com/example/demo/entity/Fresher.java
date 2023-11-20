package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "freshers")

public class Fresher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private Center center;

	private String name;
	private String language;
	private String address;
	private String email;
	private String phone;
	@Enumerated(EnumType.STRING)
	private FresherStatus status;
	private LocalDate joiningDate;

	public Fresher() {

	}

	public Fresher(int id, Center center, String name, String language, String address, String email, String phone,
			FresherStatus status, LocalDate joiningDate) {
		super();
		this.id = id;
		this.center = center;
		this.name = name;
		this.language = language;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.status = status;
		this.joiningDate = joiningDate;
	}

	public Fresher(Center center, String name, String language, String address, String email, String phone) {
		super();

		this.center = center;
		this.name = name;
		this.language = language;
		this.address = address;
		this.email = email;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Center getCenter() {
		return center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void removeCenter() {
		this.center = null;
	}

	public FresherStatus getStatus() {
		return status;
	}

	public void setStatus(FresherStatus status) {
		this.status = status;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

}
