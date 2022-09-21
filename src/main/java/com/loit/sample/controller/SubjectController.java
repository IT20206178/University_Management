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

import com.loit.sample.domain.Subject;

import com.loit.sample.model.request.SubjectRequest;

import com.loit.sample.service.SubjectService;

@RestController
@RequestMapping("/api")
public class SubjectController {
	private SubjectService subjectService;

	@Autowired
	public SubjectController(SubjectService theSubjectService) {
		subjectService = theSubjectService;
	}

	@PostMapping(value = "/subjects")
	public Subject addSubject(@RequestBody Subject subject) {
		subjectService.save(subject);
		return subject;
	}

	@GetMapping("/subjects")
	public List<Subject> findAll() {
		return subjectService.findAll();
	}

	@GetMapping(value = "/subjects/{subjectId}")
	public Subject getById(@PathVariable int subjectId) {
		Subject theSubject = subjectService.getSubjectById(subjectId);
		if (theSubject == null) {
			throw new RuntimeException("Subject id not found - " + subjectId);
		}
		return theSubject;
	}
	

	@DeleteMapping(value = "/subjects/{id}")
	public void deleteSubject(@PathVariable int id) {
		subjectService.deleteById(id);
	}

	@PutMapping(value = "/subjects/{id}")
	public Subject updateSubject(@PathVariable int id, @RequestBody SubjectRequest subjectRequest) {
		return subjectService.updateSubject(subjectRequest, id);
	}
	
	
   

}
