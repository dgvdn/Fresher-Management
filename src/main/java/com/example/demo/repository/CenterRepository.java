package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Center;

public interface CenterRepository extends JpaRepository<Center, Integer>{
	
}
