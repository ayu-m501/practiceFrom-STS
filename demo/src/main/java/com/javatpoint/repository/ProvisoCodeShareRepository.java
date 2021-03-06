package com.javatpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javatpoint.model.ProvisoCodeShareModel;

@Repository
public interface ProvisoCodeShareRepository extends JpaRepository<ProvisoCodeShareModel, String> {

	
}
