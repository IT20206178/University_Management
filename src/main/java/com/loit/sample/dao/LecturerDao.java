package com.loit.sample.dao;

import java.util.List;

import com.loit.sample.domain.Lecturer;


public interface LecturerDao {
	public void save(Lecturer lecturer);
	
	public List<Lecturer> findAll();
	
	public Lecturer getLecturerById(int id);
	
	public void deleteById(Lecturer lecturer);

	public Lecturer updateLecturer(Lecturer lecturer);

}

