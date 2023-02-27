package com.restservice.demo.beans;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Country")
public class Country {
	
	@Id
	@Column(name = "id")
	int id;
	
	@Column(name = "country_name")
	String countryName;
	
	@Column(name = "capital")
	String countryCapitalString;
	
	public Country() {
		
	}
	
	public Country(int id, String countryName, String countryCapitalString) {
		this.id = id;
		this.countryName = countryName;
		this.countryCapitalString = countryCapitalString;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCapitalString() {
		return countryCapitalString;
	}
	public void setCountryCapitalString(String countryCapitalString) {
		this.countryCapitalString = countryCapitalString;
	}
	
	

}
