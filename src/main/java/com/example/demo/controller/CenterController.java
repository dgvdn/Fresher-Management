package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Center;
import com.example.demo.service.CenterService;

@RestController
@RequestMapping("/api/centers")
public class CenterController {

	@Autowired
	private CenterService centerService;

	@GetMapping
	public ResponseEntity<List<Center>> getAllCenters() {
		List<Center> centers = centerService.getCenter();

		if (!centers.isEmpty()) {
			return new ResponseEntity<>(centers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Center> createCenter(@RequestBody Center request) {
		String name = request.getName();
		String address = request.getAddress();
		String description = request.getDescription();

		Center createdCenter = centerService.addCenter(name, address, description);

		return new ResponseEntity<>(createdCenter, HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCenter(@PathVariable int id) {
		centerService.deleteCenter(id);

		return new ResponseEntity<>("Center deleted successfully", HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Center> updateCenter(@PathVariable int id, @RequestBody Center request) {
		String name = request.getName();
		String address = request.getAddress();
		String description = request.getDescription();

		Center updatedCenter = centerService.updateCenter(id, name, address, description);

		if (updatedCenter != null) {
			return new ResponseEntity<>(updatedCenter, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/{centerId}/addFresher/{fresherId}")
	public ResponseEntity<String> addFresherToCenter(@PathVariable int centerId, @PathVariable int fresherId) {

		boolean added = centerService.addFresherToCenter(centerId, fresherId);

		if (added) {
			return new ResponseEntity<>("Fresher added to center successfully", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Fresher or Center not found", HttpStatus.NOT_FOUND);
		}
	}
}
