package com.loit.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.loit.sample.dao.CourseDao;
import com.loit.sample.domain.CourseDetails;
import com.loit.sample.exception.CourseNotFoundException;
import com.loit.sample.model.request.CourseRequest;
import com.loit.sample.model.response.CourseResponse;
import com.loit.sample.service.CourseService;

@RestController
@RequestMapping("/api")
public class CourseController {

	private CourseService courseService;

	@Autowired
	private CourseDao courseDao;

	@Autowired
	public CourseController(CourseService theCourseService) {
		courseService = theCourseService;
	}

	@PostMapping(value = "/courses")
	public ResponseEntity<CourseDetails> saveCourse(@RequestBody CourseRequest courseRequest) throws Exception {

		CourseDetails response = courseService.save(courseRequest);

		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@GetMapping("/courses")
	public List<CourseDetails> findAll() {
		return courseService.findAll();
	}

	@GetMapping(value = "/courses/{id}")
	public CourseResponse getById(@PathVariable int id) throws Exception {

		return courseService.getCourseById(id);
	
	
	}

	@DeleteMapping(value = "/courses/{id}")
	public void deleteCourse(@PathVariable int id) throws Exception{
		courseService.deleteById(id);
	}

	@PutMapping(value = "/courses/{id}")
	public CourseDetails updateCourse(@PathVariable int id, @RequestBody CourseRequest courseRequest) throws Exception {
		return courseService.updateCourse(courseRequest, id);
	}

	@PostMapping(value = "/courses/{id}/subjects/{subid}")
	public CourseDetails addSubjectToCourse(@PathVariable int id, @PathVariable int subid) {

		return courseService.addSubjectToCourse(id, subid);
	}

	@DeleteMapping(value = "/courses/{courseId}/subjects/{subjectId}")
	public void removeSubjectFromCourse(@PathVariable int subjectId, @PathVariable int courseId) {

		courseService.deleteSubjectFromCourse(courseId, subjectId);

	}

}
