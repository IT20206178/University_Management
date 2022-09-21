package com.loit.sample.service;

import java.io.Console;

import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loit.sample.dao.CourseDao;
import com.loit.sample.dao.SubjectDao;
import com.loit.sample.domain.CourseDetails;
import com.loit.sample.domain.Subject;
import com.loit.sample.model.request.CourseRequest;
import com.loit.sample.model.response.CourseResponse;
import com.loit.sample.exception.CourseAlreadyExistsException;
import com.loit.sample.exception.CourseNotFoundException;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyy");

	@Autowired
	private SubjectDao subjectDao;

	@Override
	@Transactional
	public CourseDetails save(CourseRequest courseRequest)  {
		CourseDetails courseExist = courseDao.getCourseByName(courseRequest.getCourseName());
//		if (courseExist != null) {
//			throw new CourseAlreadyExistsException("Already in exist");
//		}

		CourseDetails course = new CourseDetails();
		course.setCourseName(courseRequest.getCourseName());
		course.setCourseDescription(courseRequest.getCourseDescription());
		course.setCourseFee(courseRequest.getCourseFee());
		course.setCourseDuration(courseRequest.getCourseDuration());
		
;
		
		try {

			Date startDate = formatter.parse(courseRequest.getStartDate());
			course.setStartDate(startDate);
			Date endDate = formatter.parse(courseRequest.getEndDate());
			course.setEndDate(endDate);
//			course.setStartDate(courseRequest.getStartDate());
//			course.setEndDate(courseRequest.getEndDate());
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Kani");
		}
		

		CourseDetails response = courseDao.save(course);

		return response;
	}

	@Override
	@Transactional
	public List<CourseDetails> findAll() {
		return courseDao.findAll();
	}

	@Override
	@Transactional
	public CourseResponse getCourseById(int id) throws Exception {

		CourseDetails course = courseDao.getCourseById(id);
		if (course != null) {
			CourseResponse courseResponse = new CourseResponse();
			courseResponse.setId(course.getId());
			courseResponse.setCourseName(course.getCourseName());
			courseResponse.setCourseDescription(course.getCourseDescription());
			courseResponse.setCourseFee(course.getCourseFee());
			courseResponse.setCourseDuration(course.getCourseDuration());
			courseResponse.setStartDate(course.getStartDate());
			courseResponse.setEndDate(course.getEndDate());

			return courseResponse;
		} else {
			throw new CourseNotFoundException("Course Not Fund to id : "+id);
		}
	}

	@Override
	@Transactional
	public void deleteById(int id) throws Exception {

		CourseDetails courseDetails = courseDao.getCourseById(id);

		if (courseDetails == null) {
			throw new CourseNotFoundException("Course id not found -" + id);
		} else {
			courseDao.deleteById(courseDetails);
			System.out.println("Deleted course id - " + id);
		}
	}

	@Override
	@Transactional
	public CourseDetails updateCourse(CourseRequest courseRequest, int id) throws Exception{

		CourseDetails courseDetails = courseDao.getCourseById(id);

		if (courseDetails != null) {

			courseDetails.setCourseName(courseRequest.getCourseName());
			courseDetails.setCourseDescription(courseRequest.getCourseDescription());
			courseDetails.setCourseFee(courseRequest.getCourseFee());
			courseDetails.setCourseDuration(courseRequest.getCourseDuration());
			
			Date startDate = formatter.parse(courseRequest.getStartDate());
			courseDetails.setStartDate(startDate);
			
			Date endDate = formatter.parse(courseRequest.getEndDate());
			courseDetails.setEndDate(endDate);
//			courseDetails.setStartDate(courseRequest.getStartDate());
//			courseDetails.setEndDate(courseRequest.getEndDate());

			return courseDao.updateCourse(courseDetails);

		} else {
			throw new CourseNotFoundException("No Course Found to Update to id : " +id);
		}

	}

	@Transactional
	public CourseDetails addSubjectToCourse(int id, Integer stuid) {
		CourseDetails courseDetails = courseDao.getCourseById(id);
		Subject subject = subjectDao.getSubjectById(stuid);

		courseDetails.addSubject(subject);

		return courseDao.updateCourse(courseDetails);
	}

	@Override
	@Transactional
	public void deleteSubjectFromCourse(int courseId, int subjectId) {
		CourseDetails courseDetails = courseDao.getCourseById(courseId);
		Subject subject = subjectDao.getSubjectById(subjectId);
		courseDetails.removeSubject(subject);

		courseDao.updateCourse(courseDetails);

	}

}
