package com.loit.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loit.sample.model.request.CourseRequest;
import com.loit.sample.model.response.StudentResponse;
import com.loit.sample.model.webAPI.EmployeeResponse;
import com.loit.sample.service.WebApiService;

@RestController
@RequestMapping(value = "/employeeApi")
public class WebApiServiceController {
	
	@Autowired
	WebApiService webApiService;

	@GetMapping(value = "/employees")
	public EmployeeResponse[] getEmployeeList() {

		EmployeeResponse[] response = webApiService.getEmployeeList();
		return response;
	}
	
	@PostMapping(value = "/employees")
	public String createProducts(@RequestBody CourseRequest courseRequest) {
		
		String response = webApiService.createEmployee(courseRequest);
		return response;
	  }
	
	@GetMapping(value = "/employees/{id}")
	public EmployeeResponse getById(@PathVariable int id) {

		EmployeeResponse employeeResponse = webApiService.getById(id);
		return employeeResponse;
	}
	
	@GetMapping(value = "/employees/search")
	public List<StudentResponse> searchStudent(@RequestParam(value = "firstName", required = false) String firstName,
	@RequestParam(value = "lastName", required = false) String lastName,
	@RequestParam(value = "nic", required = false) String nic)
	{
		return webApiService.searchStudent(firstName, lastName, nic);
		
	}
	

	@PutMapping(value = "/employees/{id}")
	public void updateEmployeeById(@PathVariable("id") int id,@RequestBody CourseRequest courseRequest) {
		
		 webApiService.updateEmployeeById(id,courseRequest);
		
	}
	
	@DeleteMapping(value = "/employees/{id}")
	public void deleteEmployeeById(@PathVariable("id") int id ) {
		
		 webApiService.deleteEmployeetById(id);
		
	}

}
