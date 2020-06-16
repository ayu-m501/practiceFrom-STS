package com.javatpoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.javatpoint.model.ProvisoMainModel;
import com.javatpoint.repository.ProvisoMainModelRepository;

public class ProvisoMainModelService {

	@Autowired
	ProvisoMainModelRepository provisoMainModelRepository;
	
	public List<ProvisoMainModel> search(String s1,String s2,String s3,String s4,String s5 ) {
		return provisoMainModelRepository.findAll();
	}

}
