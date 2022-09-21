package com.loit.sample.service;

import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.loit.sample.enums.ApplicationError;
import com.loit.sample.exception.ApplicationException;

import com.loit.sample.model.request.CourseRequest;

import com.loit.sample.model.response.StudentResponse;
import com.loit.sample.model.webAPI.EmployeeResponse;

@Service
public class WebApiServiceImpl implements WebApiService {

	String GET_ALL = "https://jsonplaceholder.typicode.com/posts";
	String CREATE = "http://localhost:8080/api/courses";
	String DELETE = "http://localhost:8080/api/courses";
	String UPDATE = "http://localhost:8080/api/courses";

	@Autowired
	private RestTemplate restTemplate;

	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	@Override
	public EmployeeResponse[] getEmployeeList() {
		ResponseEntity<EmployeeResponse[]> response = restTemplate.getForEntity(GET_ALL, EmployeeResponse[].class);

		try {
			if (response.hasBody()) {

				HttpStatus httpStatus = response.getStatusCode();

				if (httpStatus.equals(HttpStatus.OK)) {
					EmployeeResponse[] employees = response.getBody();
					return employees;
				} else {
					throw new ApplicationException(ApplicationError.NOT_FOUND);
				}
			} else {
				throw new ApplicationException(ApplicationError.NOT_EXSIST);
			}

		} catch (ApplicationException ap) {
			throw new ApplicationException(ApplicationError.NOT_FOUND);
		}

	}

	@Override
	public String createEmployee(CourseRequest courseRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<CourseRequest> entity = new HttpEntity<CourseRequest>(courseRequest, headers);

		return restTemplate.exchange(CREATE, HttpMethod.POST, entity, String.class).getBody();
	}

	@Override
	public EmployeeResponse getById(int id) {

		ResponseEntity<EmployeeResponse[]> response = restTemplate
				.getForEntity("https://jsonplaceholder.typicode.com/posts", EmployeeResponse[].class);
		EmployeeResponse[] employeeResponses = response.getBody();
		try {
			for (EmployeeResponse employeeResponse : employeeResponses) {

				if (employeeResponse.getId() == id) {

					return employeeResponse;

				}
			}
		} catch (ApplicationException ap) {
			throw new ApplicationException(ApplicationError.NOT_FOUND);

		}
		return null;

	}

	@Override
	public List<StudentResponse> searchStudent(String firstName, String lastName, String nic)
			throws RestClientException {

		String baseUrl = "http://localhost:8080/api/students";
		String url = baseUrl + "/search"; 
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("firstName", firstName)
				.queryParam("lastName", lastName)
				.queryParam("nic", nic);
		


		ResponseEntity <StudentResponse[]> studentResponse = restTemplate.getForEntity(builder.toUriString(), StudentResponse[].class);

		return Arrays.asList(studentResponse.getBody());

	}

	@Override
	public void deleteEmployeetById(int id) {

		String Url = DELETE + "/" + id;
		restTemplate.delete(Url);

	}

	@Override
	public String updateEmployeeById(int id, CourseRequest courseRequest) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			HttpEntity<CourseRequest> entity = new HttpEntity<CourseRequest>(courseRequest, headers);
			if (entity.hasBody()) {
				ResponseEntity<String> request = restTemplate.exchange(UPDATE + "/" + id, HttpMethod.PUT, entity,
						String.class);
				HttpStatus httpstatus = request.getStatusCode();

				if (httpstatus.equals(HttpStatus.OK)) {
					return request.getBody();
				} else {
					throw new ApplicationException(ApplicationError.NOT_UPDATED);
				}
			} else {
				throw new ApplicationException(ApplicationError.NOT_EXSIST);
			}
		} catch (Exception e) {
			throw new ApplicationException(ApplicationError.NOT_EXSIST);
		}

	}
}
