package com.loit.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loit.sample.domain.Student;
import com.loit.sample.model.request.CourseRequest;
import com.loit.sample.model.request.StudentRequest;
import com.loit.sample.model.response.StudentResponse;
import com.loit.sample.model.response.SubjectResponse;
import com.loit.sample.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {

	private StudentService studentService;

	@Autowired
	public StudentController(StudentService theStudentService) {
		studentService = theStudentService;
	}

	@PostMapping(value = "/students")
	public Student addStudent(@RequestBody Student theStudent) {
		studentService.save(theStudent);
		return theStudent;
	}

	@GetMapping("/students")
	public List<StudentResponse> findAll() {
		return studentService.findAll();
	}

	@GetMapping(value = "/students/{studentId}")
	public StudentResponse getById(@PathVariable int studentId) {
		StudentResponse theStudent = studentService.getStudentById(studentId);
		if (theStudent == null) {
			throw new RuntimeException("Student id not found - " + studentId);
		}
		return theStudent;
	}
	
	@GetMapping(value = "/students/search")
	public List<StudentResponse> searchStudent(@RequestParam(value = "firstName",required = false) String firstName,
			@RequestParam(value = "lastName",required = false) String lastName,
			@RequestParam(value = "nic", required = false) String nic) throws Exception {
		return studentService.searchStudent(firstName,lastName,nic);
	}
	
	


	@DeleteMapping(value = "/stdents/{id}")
	public void deleteStudent(@PathVariable int id) {
		studentService.deleteById(id);
	}

	@PutMapping(value = "/students/{id}")
	public Student updateStudent(@PathVariable int id, @RequestBody StudentRequest studentRequest) {
		return studentService.updateStudent(studentRequest, id);
	}

	@PostMapping(value = "/students/{id}/courses")
	public Student addCoursesToStudent(@PathVariable int id, @RequestBody CourseRequest courseRequest) {

		return studentService.addCoursesToStudents(id, courseRequest);
	}

	@DeleteMapping(value = "students/{studentId}/courses/{courseId}")
	public void removeCourseFromStudent(@PathVariable int studentId, @PathVariable int courseId) {

		studentService.deleteCourseFromStudent(studentId, courseId);
	}

}
