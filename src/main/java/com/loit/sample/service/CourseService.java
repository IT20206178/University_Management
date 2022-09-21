package com.loit.sample.service;



import java.util.List;

import com.loit.sample.domain.CourseDetails;
import com.loit.sample.model.request.CourseRequest;
import com.loit.sample.model.response.CourseResponse;


public interface CourseService {
	public CourseDetails save(CourseRequest courseRequest) ;
	public List<CourseDetails> findAll() ;
	public CourseResponse getCourseById(int id) throws Exception;
	public void deleteById(int id) throws Exception;
	public CourseDetails updateCourse(CourseRequest courseRequest, int id) throws Exception; 
	public CourseDetails addSubjectToCourse(int id,Integer stuid) ;
	public void deleteSubjectFromCourse(int courseId,int subjectId) ;
	
	}
