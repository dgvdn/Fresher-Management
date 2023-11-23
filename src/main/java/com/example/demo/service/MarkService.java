package com.example.demo.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FresherResponse;
import com.example.demo.entity.Fresher;
import com.example.demo.entity.Mark;
import com.example.demo.entity.MarkCategory;
import com.example.demo.repository.FresherRepository;
import com.example.demo.repository.MarkRepository;

@Service
public class MarkService {
	@Autowired
	private MarkRepository markRepository;

	@Autowired
	private FresherRepository fresherRepository;

	public Mark saveMark(Mark mark) {
		return markRepository.save(mark);
	}

	public FresherResponse findFresherById(Long id) {
		Fresher fresher = fresherRepository.findById(id);

		FresherResponse fresherResponse = new FresherResponse();
		fresherResponse.setId(fresher.getId());
		fresherResponse.setName(fresher.getName());

		// Handle the case when center is null
		if (fresher.getCenter() != null) {
			fresherResponse.setCenterName(fresher.getCenter().getName());
		} else {
			fresherResponse.setCenterName("N/A"); // or any other appropriate default value
		}

		Mark mark = markRepository.findByFresher(fresher);

		if (mark != null) {
			fresherResponse.setMark1(mark.getMark_1());
			fresherResponse.setMark2(mark.getMark_2());
			fresherResponse.setMark3(mark.getMark_3());
			fresherResponse.setMarkAvg(mark.getMark_avg());
		} else {
			// Set default values for marks when not entered
			fresherResponse.setMark1(-1); // or any other appropriate default value
			fresherResponse.setMark2(-1);
			fresherResponse.setMark3(-1);
			fresherResponse.setMarkAvg(-1);
		}

		fresherResponse.setQuarterYear(getQuarterYear(fresher.getJoiningDate()));

		return fresherResponse;
	}

	private String getQuarterYear(LocalDate date) {
		int year = date.getYear();
		int month = date.getMonthValue();

		int quarter = (int) Math.ceil(month / 3.0);

		return "Q" + quarter + " " + year;
	}

	public Mark updateMark(Mark mark) {
		Fresher fresher = mark.getFresher();
		Mark updateMark = markRepository.findByFresher(fresher);
		updateMark.setComment(mark.getComment());
		updateMark.setFresher(mark.getFresher());
		updateMark.setMark_1(mark.getMark_1());
		updateMark.setMark_2(mark.getMark_2());
		updateMark.setMark_3(mark.getMark_3());
		updateMark.setMark_avg(mark.getMark_avg());
		markRepository.save(updateMark);
		return updateMark;
	}

	public Mark getByFresher(Fresher fresher) {
		return markRepository.findByFresher(fresher);
	}

	public Map<String, Long> getFresherCountByMarkCategory() {
		List<Mark> marks = markRepository.findAll();

		// Define your custom mark categories and corresponding names
		Map<String, Integer> markCategories = Map.of("Unsatisfactory", 40, // Adjusted the value to place it before
																			// Needs Improvement
				"Needs Improvement", 50, "Satisfactory", 60, "Good", 70, "Excellent", 80, "Outstanding", 90);

		// Count freshers in each category
		Map<String, Long> fresherCounts = marks.stream().collect(
				Collectors.groupingBy(mark -> getCategory(mark.getMark_avg(), markCategories), Collectors.counting()));

		// Sort the categories according to the custom order
		List<String> sortedCategories = markCategories.keySet().stream()
				.sorted(Comparator.comparingInt(markCategories::get)).collect(Collectors.toList());

		// Create a LinkedHashMap to maintain the order of insertion
		Map<String, Long> sortedFresherCounts = new LinkedHashMap<>();
		for (String category : sortedCategories) {
			sortedFresherCounts.put(category, fresherCounts.getOrDefault(category, 0L));
		}

		return sortedFresherCounts;
	}

	private String getCategory(float mark, Map<String, Integer> markCategories) {
		for (Map.Entry<String, Integer> entry : markCategories.entrySet()) {
			if (mark >= entry.getValue() && mark < entry.getValue() + 10) {
				return entry.getKey();
			}
		}
		return "Unsatisfactory";
	}

	public List<FresherResponse> getFreshersByMarkRange(String category) {
		List<Mark> marks = markRepository.findAll();

		// Find the corresponding MarkCategory
		MarkCategory markCategory = Arrays.stream(MarkCategory.values())
				.filter(categoryEnum -> categoryEnum.getCategoryName().equals(category)).findFirst()
				.orElseThrow(() -> new IllegalArgumentException("Invalid mark category"));

		// Filter freshers by mark category
		List<Fresher> filteredFreshers = marks.stream().filter(mark -> markCategory.isInRange(mark.getMark_avg()))
				.map(Mark::getFresher).collect(Collectors.toList());

		// Convert filtered freshers to response DTOs
		List<FresherResponse> fresherResponses = filteredFreshers.stream().map(this::mapFresherToResponse)
				.collect(Collectors.toList());

		return fresherResponses;
	}

	private FresherResponse mapFresherToResponse(Fresher fresher) {
		FresherResponse fresherResponse = new FresherResponse();
		fresherResponse.setId(fresher.getId());
		fresherResponse.setName(fresher.getName());

		// Handle the case when center is null
		if (fresher.getCenter() != null) {
			fresherResponse.setCenterName(fresher.getCenter().getName());
		} else {
			fresherResponse.setCenterName("N/A"); // or any other appropriate default value
		}

		Mark mark = markRepository.findByFresher(fresher);

		if (mark != null) {
			fresherResponse.setMark1(mark.getMark_1());
			fresherResponse.setMark2(mark.getMark_2());
			fresherResponse.setMark3(mark.getMark_3());
			fresherResponse.setMarkAvg(mark.getMark_avg());
		} else {
			// Set default values for marks when not entered
			fresherResponse.setMark1(-1); // or any other appropriate default value
			fresherResponse.setMark2(-1);
			fresherResponse.setMark3(-1);
			fresherResponse.setMarkAvg(-1);
		}

		fresherResponse.setQuarterYear(getQuarterYear(fresher.getJoiningDate()));

		return fresherResponse;
	}

}
