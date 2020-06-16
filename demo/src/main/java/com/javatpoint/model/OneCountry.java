package com.javatpoint.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "_country")
public class OneCountry {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "country_code")
	private int countryCode;
	
	@Column(name = "country_code_list")
	private String countryCodeList;

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public String getCountryCodeList() {
		return countryCodeList;
	}

	public void setCountryCodeList(String countryCodeList) {
		this.countryCodeList = countryCodeList;
	}

	
	
}
