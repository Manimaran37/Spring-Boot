package com.restservice.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restservice.demo.beans.Country;
import com.restservice.demo.controllers.CountryController;
import com.restservice.demo.services.CountryService;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan(basePackages = "com.restservice.demo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {ControllerMockMVCTests.class})
public class ControllerMockMVCTests {

	@Autowired
	MockMvc mockMvc;
	
	@Mock
	CountryService service;
	
	@InjectMocks
	CountryController controller;
	
	List<Country> countries;
	Country country;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();	
		
	}
	
	@Test @Order(1)
	public void test_getCountries() throws Exception {
		countries = new ArrayList<Country>();
		countries.add(new Country(1,"India","Delhi"));
		countries.add(new Country(2,"Japan","Tokyo"));
		
		when(service.getCountries()).thenReturn(countries);
		
		this.mockMvc.perform(get("/getcountries")).andExpect(status().isFound()).andDo(print());
	}
	
	@Test @Order(2)
	public void test_getCountryById() throws Exception{
		country = new Country(3,"UK", "London");
		int id = 3;
		when(service.getCountryById(id)).thenReturn(country);
		
		this.mockMvc.perform(get("/getcountries/{id}",id)).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath(".id").value(3)).andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("UK")).andExpect(MockMvcResultMatchers.jsonPath(".countryCapitalString").value("London")).andDo(print());
	}
	
	@Test @Order(3)
	public void test_getCountryByName() throws Exception{
		country = new Country(3,"UK", "London");
		String countryName = "UK";
		when(service.getCountryByName(countryName)).thenReturn(country);
		
		this.mockMvc.perform(get("/getcountries/countryname").param("name", countryName)).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath(".id").value(3)).andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("UK")).andExpect(MockMvcResultMatchers.jsonPath(".countryCapitalString").value("London")).andDo(print());
	}
	
	@Test @Order(4)
	public void test_addCountry() throws Exception {
		country = new Country(5,"Germany", "Berlin");
		when(service.addCountry(country)).thenReturn(country);
		
		String jsonBody = new ObjectMapper().writeValueAsString(country);
		this.mockMvc.perform(post("/addcountry").content(jsonBody).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated()).andDo(print());
	}
	
	@Test @Order(5)
	public void test_updateCountry() throws Exception{
		country = new Country(3,"UK", "London");
		int id = 3;
		when(service.getCountryById(id)).thenReturn(country);
		when(service.updateCountry(country)).thenReturn(country);
		
		String jsonBody = new ObjectMapper().writeValueAsString(country);
		this.mockMvc.perform(put("/updatecountry/{id}",id).content(jsonBody).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("UK")).andExpect(MockMvcResultMatchers.jsonPath(".countryCapitalString").value("London")).andDo(print());
	}
	
	@Test @Order(6)
	public void test_deleteCountry() throws Exception{
		country = new Country(5,"Germany", "Berlin");
		int id = 5;
		when(service.getCountryById(id)).thenReturn(country);
		this.mockMvc.perform(delete("/deletecountry/{id}",id)).andExpect(status().isOk()).andDo(print());
	}
}
