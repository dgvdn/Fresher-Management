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

import com.example.demo.entity.Fresher;
import com.example.demo.service.FresherService;

@RestController
@RequestMapping("/api/freshers")
public class FresherController {

	@Autowired
	private FresherService fresherService;

	@GetMapping
	public List<Fresher> getAllFreshers() {
		return fresherService.getFresher();
	}

	@GetMapping("/count")
	public Long countFreshers() {
		return fresherService.countFresher();
	}

	@PostMapping("/add")
	public ResponseEntity<Fresher> addFresher(@RequestBody Fresher fresher) {
		Fresher addedFresher = fresherService.addFresher(fresher);
		return new ResponseEntity<>(addedFresher, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteFresher(@PathVariable int id) {
		fresherService.deleteFresher(id);
		return new ResponseEntity<>("Fresher with ID " + id + " has been deleted.", HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Fresher> updateFresher(@PathVariable int id, @RequestBody Fresher fresher) {
		Fresher updatedFresher = fresherService.updateFresher(id, fresher);
		return new ResponseEntity<>(updatedFresher, HttpStatus.OK);
	}

	@GetMapping("/search/{name}")
	public ResponseEntity<List<Fresher>> searchFreshersByName(@PathVariable String name) {
		List<Fresher> foundFreshers = fresherService.findFresherByName(name);

		if (!foundFreshers.isEmpty()) {
			return new ResponseEntity<>(foundFreshers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/search/language/{language}")
	public ResponseEntity<List<Fresher>> searchFreshersByLanguage(@PathVariable String language) {
		List<Fresher> foundFreshers = fresherService.findFresherByProgramingLanguage(language);

		if (!foundFreshers.isEmpty()) {
			return new ResponseEntity<>(foundFreshers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/search/email/{email}")
	public ResponseEntity<List<Fresher>> searchFreshersByEmail(@PathVariable String email) {
		List<Fresher> foundFreshers = fresherService.findFresherByEmail(email);

		if (!foundFreshers.isEmpty()) {
			return new ResponseEntity<>(foundFreshers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Fresher> findFresherById(@PathVariable Long id) {
		Fresher fresher = fresherService.findFresherById(id);
		if (fresher == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(fresher, HttpStatus.OK);
		}
	}

	@GetMapping("/center/{centerId}/quarter/{quarter}/year/{year}")
	public ResponseEntity<List<Fresher>> getFreshersByCenterAndQuarterAndYear(@PathVariable int centerId,
			@PathVariable int quarter, @PathVariable int year) {
		List<Fresher> freshers = fresherService.getFreshersByCenterAndQuarterAndYear(centerId, quarter, year);

		if (!freshers.isEmpty()) {
			return new ResponseEntity<>(freshers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
