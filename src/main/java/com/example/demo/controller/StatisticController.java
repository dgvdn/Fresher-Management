package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.FresherResponse;
import com.example.demo.entity.TKFresherByCenter;
import com.example.demo.service.FresherService;
import com.example.demo.service.MarkService;

@RestController
@RequestMapping("/api/statistics")
public class StatisticController {
	@Autowired
	private FresherService fresherService;

	@Autowired
	private MarkService markService;

	@GetMapping("/centers")
	public ResponseEntity<Iterable<TKFresherByCenter>> getFresherStatisticsByCenter() {
		Iterable<TKFresherByCenter> statistics = fresherService.countFresherByCenter();
		return new ResponseEntity<>(statistics, HttpStatus.OK);
	}

	@GetMapping("/marks")
	public ResponseEntity<Map<String, Long>> getFresherCountsByRank() {
		Map<String, Long> fresherCounts = markService.getFresherCountByMarkCategory();
		return ResponseEntity.ok(fresherCounts);
	}

	@GetMapping("/status")
	public ResponseEntity<?> getFresherStatusStatistics() {
		try {
			return new ResponseEntity<>(fresherService.getFresherStatusStatistics(), HttpStatus.OK);
		} catch (Exception e) {
			// Handle exceptions, log the error, and return an appropriate response
			return new ResponseEntity<>("Failed to retrieve fresher status statistics.",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<?> getFreshersByStatus(@PathVariable String status) {
		try {
			// Log status value for debugging
			System.out.println("Status received: " + status);

			// You might want to validate the status here before passing it to the service
			// to prevent potential misuse or errors.

			// Call the service method to get freshers by status
			List<FresherResponse> freshers = fresherService.getFreshersByStatus(status);

			// Return the list of freshers
			return new ResponseEntity<>(freshers, HttpStatus.OK);
		} catch (Exception e) {
			// Log the exception for debugging
			e.printStackTrace();

			// Handle exceptions, log the error, and return an appropriate response
			return new ResponseEntity<>("Failed to retrieve freshers by status.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/marks/{category}")
	public ResponseEntity<List<FresherResponse>> getFreshersByMarkRange(@PathVariable String category) {
		try {
			List<FresherResponse> freshers = markService.getFreshersByMarkRange(category);
			return ResponseEntity.ok(freshers);
		} catch (Exception e) {
			// Handle exceptions, log the error, and return an appropriate response
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/languages")
	public ResponseEntity<Map<String, Long>> getFresherCountsByLanguage() {
		Map<String, Long> languageCounts = fresherService.getFresherCountByLanguage();
		return ResponseEntity.ok(languageCounts);
	}

	@GetMapping("/languages/{language}")
	public ResponseEntity<List<FresherResponse>> getFreshersByLanguage(@PathVariable String language) {
		try {
			List<FresherResponse> freshers = fresherService.getFreshersByLanguage(language);
			return ResponseEntity.ok(freshers);
		} catch (Exception e) {
			// Handle exceptions, log the error, and return an appropriate response
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
