package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FresherResponse;
import com.example.demo.entity.Center;
import com.example.demo.entity.Fresher;
import com.example.demo.entity.Mark;
import com.example.demo.entity.TKFresherByCenter;
import com.example.demo.repository.CenterRepository;
import com.example.demo.repository.FresherRepository;
import com.example.demo.repository.MarkRepository;

@Service
public class FresherService {

	@Autowired
	private FresherRepository fresherRepository;

	@Autowired
	private CenterRepository centerRepository;

	@Autowired
	private MarkRepository markRepository;

	public List<Fresher> getFresher() {
		List<Fresher> lf = fresherRepository.findAll();
		return lf;
	}

	public Long countFresher() {
		return fresherRepository.count();
	}

	public Fresher addFresher(Fresher fresher) {
		return fresherRepository.save(fresher);
	}

	public void deleteFresher(int id) {
		markRepository.deleteByFresherId(id);
		fresherRepository.deleteById(id);
	}

	public Fresher updateFresher(int id, Fresher fresher) {
		Fresher existingFresher = fresherRepository.findById(id).orElse(null);

		if (existingFresher != null) {
			// Update the basic details
			existingFresher.setName(fresher.getName());
			existingFresher.setLanguage(fresher.getLanguage());
			existingFresher.setAddress(fresher.getAddress());
			existingFresher.setEmail(fresher.getEmail());
			existingFresher.setPhone(fresher.getPhone());

			// Update the center if a new center ID is provided
			if (fresher.getCenter() != null) {
				Center center = centerRepository.findById(fresher.getCenter().getId()).orElse(null);
				if (center != null) {
					existingFresher.setCenter(center);
				}
			}

			return fresherRepository.save(existingFresher);
		}

		return null;
	}

	public Fresher getFresherById(int id) {
		return fresherRepository.findById(id).orElse(null);
	}

	public void calculateAndSaveMarks(int fresherId, float mark1, float mark2, float mark3, float averageMark) {
		Fresher fresher = getFresherById(fresherId);

		if (fresher != null) {
			Mark mark = new Mark(mark1, mark2, mark3, averageMark, fresher);
			markRepository.save(mark);
		}
	}

	public List<Fresher> findFresherByName(String name) {
		List<Fresher> lf = fresherRepository.findFresherByName(name);

		return lf;
	}

	public List<Fresher> findFresherByEmail(String email) {
		List<Fresher> lf = fresherRepository.findFresherByEmail(email);

		return lf;
	}

	public List<Fresher> findFresherByProgramingLanguage(String language) {
		List<Fresher> lf = fresherRepository.findFresherByLanguage(language);

		return lf;
	}

	public List<TKFresherByCenter> countFresherByCenter() {
		List<Center> lc = centerRepository.findAll();
		List<TKFresherByCenter> ltc = new ArrayList<>();

		for (Center center : lc) {
			int fresherCount = fresherRepository.countByCenter(center);
			ltc.add(new TKFresherByCenter(center, fresherCount));
		}

		return ltc;
	}

	public int countFresherByMark(int min, int max) {
		List<Mark> lm = markRepository.findAll();

		int fresherCount = 0;
		for (Mark mark : lm) {
			if (mark.getMark_avg() >= min && mark.getMark_avg() <= max)
				fresherCount++;
		}

		return fresherCount;
	}

	public Fresher findFresherById(Long id) {
		return fresherRepository.findById(id);
	}

	public List<Fresher> getFreshersByCenterAndQuarterAndYear(int centerId, int quarter, int year) {
		// Retrieve all freshers associated with the specified center
		List<Fresher> freshers = fresherRepository.findByCenterId(centerId);

		// Filter freshers based on the quarter and year
		List<Fresher> filteredFreshers = new ArrayList<>();

		for (Fresher fresher : freshers) {
			LocalDate joiningDate = fresher.getJoiningDate();
			if (joiningDate != null && isWithinQuarter(joiningDate, quarter, year)) {
				filteredFreshers.add(fresher);
			}
		}

		return filteredFreshers;
	}

	private boolean isWithinQuarter(LocalDate date, int quarter, int year) {
		int month = date.getMonthValue();

		// Assuming quarters are defined as 1-3, 4-6, 7-9, 10-12
		int quarterStartMonth = (quarter - 1) * 3 + 1;
		int quarterEndMonth = quarterStartMonth + 2;

		return date.getYear() == year && month >= quarterStartMonth && month <= quarterEndMonth;
	}

	public List<FresherResponse> getAllFreshersWithMarksAndQuarter() {
		List<Fresher> freshers = fresherRepository.findAll();
		return convertToResponseWithMarksAndQuarter(freshers);
	}

	private List<FresherResponse> convertToResponseWithMarksAndQuarter(List<Fresher> freshers) {
		return freshers.stream().map(this::convertToResponse).collect(Collectors.toList());
	}

	private FresherResponse convertToResponse(Fresher fresher) {
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

}
