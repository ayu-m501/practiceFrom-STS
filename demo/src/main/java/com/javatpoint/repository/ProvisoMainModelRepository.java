package com.javatpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javatpoint.model.ProvisoMainModel;

@Repository
public interface ProvisoMainModelRepository extends JpaRepository<ProvisoMainModel, String>{

	
}
