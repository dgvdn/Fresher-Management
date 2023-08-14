package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
