package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TKFresherByCenter;
import com.example.demo.service.FresherService;

@RestController
@RequestMapping("/api/centers")
public class CenterStatisticController {

	@Autowired
	private FresherService fresherService;

	@GetMapping("/statistics")
	public ResponseEntity<Iterable<TKFresherByCenter>> getFresherStatisticsByCenter() {
		Iterable<TKFresherByCenter> statistics = fresherService.countFresherByCenter();
		return new ResponseEntity<>(statistics, HttpStatus.OK);
	}
}
