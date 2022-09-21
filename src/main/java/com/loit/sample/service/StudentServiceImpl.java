package com.loit.sample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loit.sample.dao.CourseDao;
import com.loit.sample.dao.StudentDao;
import com.loit.sample.domain.CourseDetails;
import com.loit.sample.domain.Student;
import com.loit.sample.model.request.CourseRequest;
import com.loit.sample.model.request.StudentRequest;
import com.loit.sample.model.response.StudentResponse;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
//	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

	@Autowired
	private CourseDao courseDao;

	@Override
	@Transactional
	public void save(Student theStudent) {
		studentDao.save(theStudent);
	}

	@Override
	@Transactional
	public List<StudentResponse> findAll() {
		List<Student> list = studentDao.findAll();
		List<StudentResponse> studentResponseList = new ArrayList<>();
		for (Student student : list) {
			StudentResponse studentResponse = new StudentResponse();
			studentResponse.setId(student.getId());
			studentResponse.setFirstName(student.getFirstName());
			studentResponse.setLastNameString(student.getLastName());
			studentResponse.setAddress(student.getAddress());
			studentResponse.setPhoneNumber(student.getPhoneNumber());
			studentResponse.setEmail(student.getEmail());
			studentResponse.setNic(student.getNic());
			studentResponse.setGender(student.getGender());
			studentResponse.setDob(student.getDob());
			studentResponse.setSchool(student.getSchool());
			studentResponse.setStartDate(student.getStartDate());

			studentResponseList.add(studentResponse);
		}
		return studentResponseList;
	}

	@Override

	public StudentResponse getStudentById(int id) {
		Student student = studentDao.getStudentById(id);
		StudentResponse studentResponse = new StudentResponse();
		studentResponse.setId(student.getId());
		studentResponse.setFirstName(student.getFirstName());
		studentResponse.setLastNameString(student.getLastName());
		studentResponse.setAddress(student.getAddress());
		studentResponse.setPhoneNumber(student.getPhoneNumber());
		studentResponse.setEmail(student.getEmail());
		studentResponse.setNic(student.getNic());
		studentResponse.setGender(student.getGender());
		studentResponse.setDob(student.getDob());
		studentResponse.setSchool(student.getSchool());
		studentResponse.setStartDate(student.getStartDate());

		return studentResponse;
	}

	@Override
	public List<StudentResponse> searchStudent(String firstName, String lastName, String nic) {
		List<StudentResponse> finaList = new ArrayList<StudentResponse>();
		List<Student> finaList2 = studentDao.searchStudent(firstName, lastName, nic);
		
			for (Student student : finaList2) {
				StudentResponse studentResponse = new StudentResponse();
				studentResponse.setId(student.getId());
				studentResponse.setFirstName(student.getFirstName());
				studentResponse.setLastNameString(student.getLastName());
				studentResponse.setAddress(student.getAddress());
				studentResponse.setPhoneNumber(student.getPhoneNumber());
				studentResponse.setEmail(student.getEmail());
				studentResponse.setNic(student.getNic());
				studentResponse.setGender(student.getGender());
				studentResponse.setDob(student.getDob());
				studentResponse.setSchool(student.getSchool());
				studentResponse.setStartDate(student.getStartDate());

				finaList.add(studentResponse);
			}
			return finaList;
			
		}
		

	

	@Override
	@Transactional
	public void deleteById(int id) {

		Student student = studentDao.getStudentById(id);
		studentDao.deleteById(student);

	}

	@Override
	@Transactional
	public Student updateStudent(StudentRequest studenteRequest, int id) {

		Student student = studentDao.getStudentById(id);

		student.setFirstName(studenteRequest.getFirstName());
		student.setLastName(studenteRequest.getLastName());
		student.setAddress(studenteRequest.getAddress());
		student.setPhoneNumber(studenteRequest.getPhoneNumber());
		student.setEmail(studenteRequest.getEmail());
		student.setNic(studenteRequest.getNic());
		student.setGender(studenteRequest.getGender());
		student.setDob(studenteRequest.getDob());
		student.setSchool(studenteRequest.getSchool());
		student.setStartDate(studenteRequest.getStartDate());

		return studentDao.updateStudent(student);

	}

	@Override
	@Transactional
	public Student addCoursesToStudents(int id, CourseRequest courseRequest) {
		Student student = studentDao.getStudentById(id);
		CourseDetails courseDetails = courseDao.getCourseById(courseRequest.getId());
		student.addCourses(courseDetails);// add courses to student list

		return studentDao.updateStudent(student);
	}

	@Override
	@Transactional
	public void deleteCourseFromStudent(int studentId, int courseId) {

		Student student = studentDao.getStudentById(studentId);
		CourseDetails courseDetails = courseDao.getCourseById(courseId);
		student.removeCourse(courseDetails);

		studentDao.updateStudent(student);
	}
}
