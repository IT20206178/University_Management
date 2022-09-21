package com.loit.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.loit.sample.dao.LecturerDao;
import com.loit.sample.dao.LecturerSubjectDao;
import com.loit.sample.dao.SubjectDao;
import com.loit.sample.domain.Lecturer;
import com.loit.sample.domain.LecturerSubject;
import com.loit.sample.domain.Subject;
import com.loit.sample.model.request.LecturerRequest;
import com.loit.sample.model.request.SubjectRequest;

@Service
public class LecturerServiceImpl implements LecturerService{
	@Autowired
	private LecturerDao lecturerDao;
	
	@Autowired
	private SubjectDao subjectDao;
	
	@Autowired
	private LecturerSubjectDao lecturerSubjectDao;



	
	@Override
	@Transactional
	public void save(Lecturer theLecturer) {
		lecturerDao.save(theLecturer);
	}

	@Override
	@Transactional
	public List<Lecturer> findAll() {
		return lecturerDao.findAll();
	}

	@Override
	@Transactional
	public Lecturer getLecturerById(int id) {
		return lecturerDao.getLecturerById(id);
	}

	@Override
	@Transactional
	public void deleteById(int id) {

		Lecturer lecturer = lecturerDao.getLecturerById(id);

		if (lecturer == null) {
			throw new RuntimeException("Lecturer id not found -" + id);
		} else {
			lecturerDao.deleteById(lecturer);
			System.out.println("Deleted lecturer id - " + id);
		}
	}

	@Override
	@Transactional
	public Lecturer updateLecturer(LecturerRequest lecturereRequest, int id) {

		Lecturer lecturer = lecturerDao.getLecturerById(id);

		lecturer.setFirstName(lecturereRequest.getFirstName());
		lecturer.setLastName(lecturereRequest.getLastName());
		lecturer.setAddress(lecturereRequest.getAddress());
		lecturer.setPhoneNumber(lecturereRequest.getPhoneNumber());
		lecturer.setEmail(lecturereRequest.getEmail());
		lecturer.setNic(lecturereRequest.getNic());
		lecturer.setGender(lecturereRequest.getGender());
		lecturer.setDob(lecturereRequest.getDob());
		lecturer.setDegree(lecturereRequest.getDegree());
		lecturer.setRegistrationDate(lecturereRequest.getRegistrationDate());

		return lecturerDao.updateLecturer(lecturer);

	}
	

	@Override
	@Transactional
	public LecturerSubject addSubjectToLecturer(int lecturerId, SubjectRequest subjectRequest) {

		Lecturer lecturer = lecturerDao.getLecturerById(lecturerId);
		Subject subject = subjectDao.getSubjectById(subjectRequest.getId());
		LecturerSubject lecturerSubject = new LecturerSubject();
		lecturerSubject.setLecturer(lecturer);
		lecturerSubject.setSubject(subject);
		
		return lecturerSubjectDao.saveSubjectToLecturer(lecturerSubject);

	}

	@Override
	@Transactional
	public void deleteSubjectFromLecturer(int lectureId, int subjectId) {

		LecturerSubject lecturerSubject = lecturerSubjectDao.getLecturerSubject(lectureId, subjectId);
		lecturerSubjectDao.deleteLecturerSubject(lecturerSubject);
	}

	

}
