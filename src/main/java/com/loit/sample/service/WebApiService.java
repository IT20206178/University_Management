package com.loit.sample.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.loit.sample.model.request.CourseRequest;
import com.loit.sample.model.response.CourseResponse;
import com.loit.sample.model.response.StudentResponse;
import com.loit.sample.model.webAPI.EmployeeResponse;

public interface WebApiService {
	
	public EmployeeResponse[] getEmployeeList();
	public String createEmployee(CourseRequest courseRequest);
	public EmployeeResponse getById(int id);
	public List<StudentResponse> searchStudent(String firstName, String lastName, String nic) throws RestClientException;
	
	public void deleteEmployeetById(int id);
	public String updateEmployeeById(int id, CourseRequest courseRequest);


}
