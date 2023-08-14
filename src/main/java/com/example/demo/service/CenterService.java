package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Center;
import com.example.demo.entity.Fresher;
import com.example.demo.repository.CenterRepository;
import com.example.demo.repository.FresherRepository;

@Service
public class CenterService {
	@Autowired
	private CenterRepository centerRepository;
	@Autowired
	private FresherRepository fresherRepository;

	public List<Center> getCenter() {
		List<Center> lc = centerRepository.findAll();
		return lc;
	}

	public Center addCenter(String name, String address, String description) {
		Center center = new Center(name, address, description);

		return centerRepository.save(center);
	}

	public void deleteCenter(int id) {
		Center center = centerRepository.findById(id).orElse(null);

		if (center != null) {
			List<Fresher> freshers = fresherRepository.findByCenter(center);
			for (Fresher fresher : freshers) {
				fresher.setCenter(null);
			}

			centerRepository.deleteById(id);
		}
	}

	public Center updateCenter(int id, String name, String address, String description) {
		Center center = centerRepository.findById(id).get();

		center.setName(name);
		center.setAddress(address);
		center.setDescription(description);

		return centerRepository.save(center);
	}

	public boolean addFresherToCenter(int centerId, int fresherId) {
		Center center = centerRepository.findById(centerId).orElse(null);
		Fresher fresher = fresherRepository.findById(fresherId).orElse(null);

		if (center != null && fresher != null) {
			fresher.setCenter(center);
			fresherRepository.save(fresher);
			return true;
		}

		return false;

	}
}
