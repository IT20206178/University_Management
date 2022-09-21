package com.loit.sample.dao;

import java.util.List;

import com.loit.sample.domain.CourseDetails;

public interface CourseDao {
	
	public CourseDetails save(CourseDetails courseDetails);
	
	public List<CourseDetails> findAll();
	
	public CourseDetails getCourseById(int id);
	
	public void deleteById(CourseDetails courseDetails);

	public CourseDetails updateCourse(CourseDetails courseDetails);
	
	public CourseDetails getCourseByName(String name);
	


}
