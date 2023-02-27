package com.restservice.demo;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.restservice.demo.beans.Country;
import com.restservice.demo.controllers.CountryController;
import com.restservice.demo.services.CountryService;

@SpringBootTest(classes = {ControllerMockitoTests.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ControllerMockitoTests {

	@Mock
	CountryService service;
	
	@InjectMocks
	CountryController controller;
	
	List<Country> countries;
	Country country;
	
	@Test @Order(1)
	public void test_getCountries() {
		countries = new ArrayList<Country>();
		countries.add(new Country(1,"India","Delhi"));
		countries.add(new Country(2,"Japan","Tokyo"));
		
		when(service.getCountries()).thenReturn(countries);
		ResponseEntity<List<Country>> res = controller.getCountries();
		Assertions.assertEquals(HttpStatus.FOUND ,res.getStatusCode());
		Assertions.assertEquals(2, res.getBody().size());
	}
	
	@Test @Order(2)
	public void test_getCountryById() {
		country = new Country(3,"UK", "London");
		int id = 3;
		when(service.getCountryById(id)).thenReturn(country);
		ResponseEntity<Country> res = controller.getCountryByID(id);
		Assertions.assertEquals(HttpStatus.OK ,res.getStatusCode());
		Assertions.assertEquals(id, res.getBody().getId());
	}
	
	@Test @Order(3)
	public void test_getCountryByName() {
		country = new Country(4,"USA", "Washington");
		String name = "USA";
		when(service.getCountryByName(name)).thenReturn(country);
		ResponseEntity<Country> res = controller.getCountryByName(name);
		Assertions.assertEquals(HttpStatus.OK ,res.getStatusCode());
		Assertions.assertEquals(name, res.getBody().getCountryName());
	}
	
	@Test @Order(4)
	public void test_addCountry() {
		country = new Country(5,"Germany", "Berlin");
		when(service.addCountry(country)).thenReturn(country);
		ResponseEntity<Country> res =  controller.addCountry(country);
		Assertions.assertEquals(HttpStatus.CREATED ,res.getStatusCode());
		Assertions.assertEquals(country, res.getBody());
	}
	
	@Test @Order(5)
	public void test_updateCountry() {
		country = new Country(5,"Germany", "Berlin");
		int id = 5;
		when(service.getCountryById(id)).thenReturn(country);
		when(service.updateCountry(country)).thenReturn(country);
		ResponseEntity<Country> res = controller.updateCountry(id, country);
		Assertions.assertEquals(5, res.getBody().getId());
		Assertions.assertEquals("Germany", res.getBody().getCountryName());
		Assertions.assertEquals("Berlin", res.getBody().getCountryCapitalString());
//		Assertions.assertEquals(HttpStatus.OK ,res.getStatusCode());
//		Assertions.assertEquals(country, res.getBody());
	}
	
	@Test @Order(6)
	public void test_deleteCountry() {
		country = new Country(5,"Germany", "Berlin");
		int id = 5;
		when(service.getCountryById(id)).thenReturn(country);
		ResponseEntity<Country> res = controller.deleteCountry(id);
		Assertions.assertEquals(HttpStatus.OK ,res.getStatusCode());
	}
}
