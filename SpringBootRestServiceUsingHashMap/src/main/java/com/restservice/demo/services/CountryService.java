package com.restservice.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.restservice.demo.beans.Country;
import com.restservice.demo.controllers.AddResponse;
import com.restservice.demo.repository.CountryRepository;

@Component
@Service
public class CountryService {
	
	@Autowired
	CountryRepository countryRep;
	
	public List<Country> getCountries() {
		return countryRep.findAll();

	}
	
	public Country getCountryById(int id) {
		
		List<Country> countries = countryRep.findAll();
		Country country = null;
		
		for(Country con: countries) {
			if(con.getId()==id) {
				country = con;
			}
		}
		return country;
	}

	public Country getCountryByName(String name) {
		List<Country> countries = countryRep.findAll();
		Country country = null;
		
		for(Country con: countries) {
			if(con.getCountryName().equalsIgnoreCase(name)) {
				country = con;
			}
		}
		return country;
		
	}
	
	public int getMaxID() {
		return countryRep.findAll().size()+1;
	}
	
	public Country addCountry(Country country) {
		country.setId(getMaxID());
		countryRep.save(country);
		return country; 
	}
	
	public Country updateCountry(Country country) {
		countryRep.save(country);
		return country;
	}
	
	//Deletion With message and id info
	public AddResponse deleteCountry(int id) {
		countryRep.deleteById(id);
		AddResponse response = new AddResponse();	
		response.setMsg("Country Deleted!");
		response.setId(id);
		return response;
	}
	
	//Deletion without message
	public void deleteCountry(Country country) {
		countryRep.delete(country);
	}
}
