package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Mark;

public interface MarkRepository extends JpaRepository<Mark, Integer> {

	@Transactional
	void deleteByFresherId(int fresherId);
}
