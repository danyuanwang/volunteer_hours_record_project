package com.packt.volunteerhoursrecorder.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packt.volunteerhoursrecorder.domain.HourRepository;
import com.packt.volunteerhoursrecorder.domain.Hours;

@RestController

public class HourController {
	@Autowired
	private HourRepository repository;
	@RequestMapping("/hours")
	public Iterable<Hours> getHours() {
		return repository.findAll();
	}
}