package com.loit.sample.dao;

import java.util.List;

import com.loit.sample.domain.Subject;
import com.loit.sample.model.response.SubjectResponse;


public interface SubjectDao {
	public Subject save(Subject subject);
	public List<Subject> findAll();
	public Subject getSubjectById(int id);
	public void deleteById(Subject subject);
	public Subject updateSubject(Subject subject);
	
	

}
