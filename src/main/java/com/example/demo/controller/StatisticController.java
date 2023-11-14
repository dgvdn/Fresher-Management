package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.FresherCountResponse;
import com.example.demo.entity.TKFresherByCenter;
import com.example.demo.exception.InvalidMarkRangeException;
import com.example.demo.service.FresherService;

@RestController
@RequestMapping("/api/statistics")
public class StatisticController {
	@Autowired
	private FresherService fresherService;

	@GetMapping("/centers")
	public ResponseEntity<Iterable<TKFresherByCenter>> getFresherStatisticsByCenter() {
		Iterable<TKFresherByCenter> statistics = fresherService.countFresherByCenter();
		return new ResponseEntity<>(statistics, HttpStatus.OK);
	}

	@GetMapping("/marks")
	public ResponseEntity<FresherCountResponse> getFresherCountByMarkRange(@RequestParam int minMark,
			@RequestParam int maxMark) {
		if (minMark < 0 || maxMark < 0 || minMark > maxMark) {
			throw new InvalidMarkRangeException(minMark, maxMark);
		}

		int fresherCount = fresherService.countFresherByMark(minMark, maxMark);
		FresherCountResponse response = new FresherCountResponse(fresherCount);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
