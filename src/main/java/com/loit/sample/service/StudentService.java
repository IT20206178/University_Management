package com.loit.sample.service;

import java.util.List;

import com.loit.sample.domain.Student;
import com.loit.sample.model.request.CourseRequest;
import com.loit.sample.model.request.StudentRequest;
import com.loit.sample.model.response.StudentResponse;

public interface StudentService {

	public void save(Student theStudent);

	public List<StudentResponse> findAll();

	public StudentResponse getStudentById(int id);
	
	public List<StudentResponse> searchStudent(String firstName,String lastName,String nic)throws Exception;


	public void deleteById(int id);

	public Student updateStudent(StudentRequest studentRequest, int id);
	
	public Student addCoursesToStudents(int id, CourseRequest courseRequest);
	public void deleteCourseFromStudent(int studentId, int courseId);
}
