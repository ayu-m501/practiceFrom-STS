package com.javatpoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.javatpoint.model.ProvisoCodeShareModel;
import com.javatpoint.service.ProvisoCodeShareService;

public class provisoCodeShareCTL {
	
	@Autowired
	ProvisoCodeShareService provisoCodeShareService;

	public List<ProvisoCodeShareModel> search(String s1,String s2,String s3,String s4,String s5 ){
		return provisoCodeShareService.search(s1, s2, s3, s4, s5);
	}
}
