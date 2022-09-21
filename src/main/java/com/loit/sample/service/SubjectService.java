package com.loit.sample.service;

import java.util.List;

import com.loit.sample.domain.Subject;
import com.loit.sample.model.request.SubjectRequest;
import com.loit.sample.model.response.SubjectResponse;

public interface SubjectService {

	public void save(Subject subject);
	public List<Subject> findAll();
	public Subject getSubjectById(int id);
	public void deleteById(int id);
	public Subject updateSubject(SubjectRequest subjectRequest,int id); 
}
