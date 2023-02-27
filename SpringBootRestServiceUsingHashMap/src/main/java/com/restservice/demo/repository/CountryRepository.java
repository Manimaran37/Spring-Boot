package com.restservice.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restservice.demo.beans.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{

	
}
