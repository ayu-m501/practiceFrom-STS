package com.javatpoint.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.model.OneCountry;
import com.javatpoint.service.OneCountryService;

@RestController
@RequestMapping(value = "/ctl/oneCountry")
public class OneCountryController {

	@Autowired
	OneCountryService oneCountryService;

	@GetMapping("/getAllCountryList")
	public List<OneCountry> getAllCountryCode() {
     return oneCountryService.getAllCountryCode();

	}

	@RequestMapping(method = RequestMethod.POST)
	public OneCountry createOneCountryCode(@RequestBody @Valid OneCountry oneCountry) {
		oneCountryService.createOneCountry(oneCountry);
		return oneCountry;
	}
}
