package com.javatpoint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javatpoint.model.OneCountry;

@Repository
public interface OneCountryRepository extends JpaRepository<OneCountry, Integer>{

}