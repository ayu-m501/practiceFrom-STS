package com.javatpoint.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatpoint.model.OneCountry;
import com.javatpoint.repository.OneCountryRepository;

@Service
public class OneCountryService {

	@Autowired
	OneCountryRepository oneCountryRepository;
	
	public List<OneCountry> getAllCountryCode(){
	return	oneCountryRepository.findAll();
	}

	public void createOneCountry(OneCountry oneCountry) {

		Set<String> set = new HashSet<String>();
		StringBuilder stringBuilder = new StringBuilder();

		String countryCodeList = oneCountry.getCountryCodeList();
		String[] counCodetryListArray = countryCodeList.split(",");

		for (String countryCodeString : counCodetryListArray) {
			set.add(countryCodeString);
		}
		for (String oneCountryCode : set) {
			stringBuilder = stringBuilder.append(oneCountryCode).append(",");
		}
		String oneCountrycodeList = stringBuilder.toString();
		int lastIndexNo = oneCountrycodeList.lastIndexOf(",");
		String newCountryCodeList = oneCountrycodeList.substring(0, lastIndexNo);
		oneCountry.setCountryCodeList(newCountryCodeList);
		oneCountryRepository.save(oneCountry);

	}
}