package com.javatpoint.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.javatpoint.model.ProvisoMainModel;
import com.javatpoint.service.ProvisoMainModelService;

public class ProvisoMainModelCTL {

	@Autowired
	private ProvisoMainModelService provisoMainModelService;
	
	public List<ProvisoMainModel> search(String s1,String s2,String s3,String s4,String str ){
	return	provisoMainModelService.search(s1, s2, s3, s4, str);
	}
}
