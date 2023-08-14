package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.FresherService;

@RestController
@RequestMapping("/api/freshers")
public class MarkStatisticController {

	@Autowired
	private FresherService fresherService;

	@GetMapping("/markStatistics")
	public ResponseEntity<Integer> getFresherCountByMarkRange(@RequestParam int minMark, @RequestParam int maxMark) {

		int fresherCount = fresherService.countFresherByMark(minMark, maxMark);
		return new ResponseEntity<>(fresherCount, HttpStatus.OK);
	}
}
