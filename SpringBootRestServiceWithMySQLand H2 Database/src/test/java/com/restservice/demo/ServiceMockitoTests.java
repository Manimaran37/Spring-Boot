package com.restservice.demo;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.restservice.demo.beans.Country;
import com.restservice.demo.repository.CountryRepository;
import com.restservice.demo.services.CountryService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = {ServiceMockitoTests.class})
public class ServiceMockitoTests {
	
	@Mock
	CountryRepository repo;
	
	@InjectMocks
	CountryService service;
	
	public List<Country> countries;
	
	@Test
	@Order(1)
	public void test_getCountries() {
		countries = new ArrayList<Country>();
		countries.add(new Country(1,"India","Delhi"));
		countries.add(new Country(2,"Japan","Tokyo"));
		when(repo.findAll()).thenReturn(countries);
		Assertions.assertEquals(2, service.getCountries().size());
	}
	
	@Test @Order(2)
	public void test_getCountryById() {
		List<Country> countries = new ArrayList<Country>();
		countries.add(new Country(1,"India","Delhi"));
		countries.add(new Country(2,"Japan","Tokyo"));
		int id=1;
		when(repo.findAll()).thenReturn(countries);
		Assertions.assertEquals(id, service.getCountryById(id).getId()); 	
		
	}
	
	@Test @Order(3)
	public void test_getCountryByName() {
		List<Country> countries = new ArrayList<Country>();
		countries.add(new Country(1,"India","Delhi"));
		countries.add(new Country(2,"Japan","Tokyo"));
		String name = "India";
		when(repo.findAll()).thenReturn(countries);
		Assertions.assertEquals(name, service.getCountryByName(name).getCountryName()); 	
		
	}
	
	@Test @Order(4)
	public void test_addCountry() {
		Country country = new Country(1, "UK", "London");
		when(repo.save(country)).thenReturn(country);
	
		Assertions.assertEquals(country,service.addCountry(country));
	}
	
	@Test @Order(5)
	public void test_updateCountry() {
		Country country = new Country(1, "UK", "London");
		when(repo.save(country)).thenReturn(country);
	
		Assertions.assertEquals(country,service.addCountry(country));
	}
	
	@Test @Order(6)
	public void test_deleteCountry() {
		Country country = new Country(1, "UK", "London");
		service.deleteCountry(country);
		verify(repo, times(1)).delete(country);
	}
}
