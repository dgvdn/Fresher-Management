package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FresherResponse;
import com.example.demo.entity.Fresher;
import com.example.demo.entity.Mark;
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
}
