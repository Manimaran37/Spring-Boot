package com.restservice.demo;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersController {

	@GetMapping
	public String getUsers(@RequestParam(value = "page") int pageNo, @RequestParam(value = "limit") int limitNo) {
		
		return "http GET request sent for page " + pageNo + " and limit " + limitNo;
	}
	
	@GetMapping(path="/{userID}")  //Path parameter
	public String getUserWithPathParam(@PathVariable String userID) {
		
		return "http GET request sent for " + userID;
	}
	
//	@GetMapping("/users/query")
//	public String getUserWithRequestParam(@RequestParam(value = "page") int pageNo, @RequestParam(value = "limit") int limitNo) {
//		
//		return "http GET request sent for page " + pageNo + " and limit " + limitNo;
//	}
	
	@PostMapping
	public String createUser() {
		
		return "http POST request triggered";
	}
	
	@PutMapping
	public String updateUser() {
		
		return "http PUT request triggered";
	}
	
	@DeleteMapping
	public String deleteUser() {
		
		return "http Delete request triggered";
	}
	
}
