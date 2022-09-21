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
import org.springframework.web.bind.annotation.RestController;

import com.loit.sample.domain.Lecturer;
import com.loit.sample.domain.LecturerSubject;
import com.loit.sample.model.request.LecturerRequest;
import com.loit.sample.model.request.SubjectRequest;
import com.loit.sample.service.LecturerService;

@RestController
@RequestMapping("/api")
public class LecturerController {
	
	private LecturerService lecturerService;

	@Autowired
	public LecturerController(LecturerService theLecturerService) {
		lecturerService = theLecturerService;
	}

	@PostMapping(value = "/lecturers")
	public Lecturer addLecturer(@RequestBody Lecturer theLecturer) {
		lecturerService.save(theLecturer);
		return theLecturer;
	}

	@GetMapping("/lecturers")
	public List<Lecturer> findAll() {
		return lecturerService.findAll();
	}

	@GetMapping(value = "/lecturers/{lecturerId}")
	public Lecturer getById(@PathVariable int lecturerId) {
		Lecturer theLecturer = lecturerService.getLecturerById(lecturerId);
		if (theLecturer == null) {
			throw new RuntimeException("Lecturer id not found - " + lecturerId);
		}
		return theLecturer;
	}

	@DeleteMapping(value = "/lecturers/{id}")
	public void deleteLecturer(@PathVariable int id) {
		lecturerService.deleteById(id);
	}

	@PutMapping(value = "/lecturers/{id}")
	public Lecturer updateLecturer(@PathVariable int id, @RequestBody LecturerRequest lecturerRequest) {
		return lecturerService.updateLecturer(lecturerRequest, id);
	}
	
	@PostMapping(value = "/lecturers/{id}/subjects")
	public LecturerSubject addSubjectsToLecturer (@PathVariable int id, @RequestBody SubjectRequest subjectRequest) {
		
		return lecturerService.addSubjectToLecturer(id, subjectRequest);
	}

	@DeleteMapping(value = "/lecturers/{lectureId}/subjects/{subjectId}")
	public void removeSubjectsFromLecturer (@PathVariable int lectureId,@PathVariable int subjectId) {
		
		 lecturerService.deleteSubjectFromLecturer(lectureId,subjectId);
	}

}
