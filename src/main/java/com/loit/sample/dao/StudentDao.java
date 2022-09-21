package com.loit.sample.dao;

import java.util.List;


import com.loit.sample.domain.Student;
import com.loit.sample.model.response.StudentResponse;

public interface StudentDao {

	public void save(Student student);
	
	public List<Student> findAll();
	
	public Student getStudentById(int id);
	
	public List<Student> searchStudent(String firstName,String lastName, String nic);
	
	public void deleteById(Student student);

	public Student updateStudent(Student student);
	
}
