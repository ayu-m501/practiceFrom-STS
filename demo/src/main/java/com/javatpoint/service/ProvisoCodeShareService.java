package com.javatpoint.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.javatpoint.model.ProvisoCodeShareModel;
import com.javatpoint.repository.ProvisoCodeShareRepository;

public class ProvisoCodeShareService {

	@Autowired
	ProvisoCodeShareRepository provisoCodeShareRepository;
	
	public List<ProvisoCodeShareModel> search(String s1,String s2,String s3,String s4,String s5 ) {
		return provisoCodeShareRepository.findAll();
	}

}
