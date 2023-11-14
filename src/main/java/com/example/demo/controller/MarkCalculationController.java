package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.MarkCalculationRequest;
import com.example.demo.dto.MarkCalculationResponse;
import com.example.demo.entity.Fresher;
import com.example.demo.service.FresherService;

@RestController
@RequestMapping("/api/marks")
public class MarkCalculationController {

	@Autowired
	private FresherService fresherService;

	@PostMapping("/calculate")
	public ResponseEntity<MarkCalculationResponse> calculateMarks(@RequestBody MarkCalculationRequest request) {
		Fresher fresher = fresherService.getFresherById(request.getFresherId());

		if (fresher != null) {
			float mark1 = request.getMark1();
			float mark2 = request.getMark2();
			float mark3 = request.getMark3();

			float averageMark = (mark1 + mark2 + mark3) / 3;

			fresherService.calculateAndSaveMarks(fresher.getId(), mark1, mark2, mark3, averageMark);

			MarkCalculationResponse response = new MarkCalculationResponse();
			response.setAverageMark(averageMark);
			response.setResultMessage("Marks calculated and saved successfully.");

			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
