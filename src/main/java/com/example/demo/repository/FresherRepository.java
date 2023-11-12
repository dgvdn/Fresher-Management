package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Center;
import com.example.demo.entity.Fresher;

public interface FresherRepository extends JpaRepository<Fresher, Integer> {

	List<Fresher> findFresherByName(String name);

	List<Fresher> findFresherByEmail(String email);

	List<Fresher> findFresherByLanguage(String language);

	int countByCenter(Center center);

	List<Fresher> findByCenter(Center center);

	Fresher findById(Long id);
}