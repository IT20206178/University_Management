package com.loit.sample.service;

import java.util.List;

import com.loit.sample.domain.Lecturer;
import com.loit.sample.domain.LecturerSubject;
import com.loit.sample.model.request.LecturerRequest;
import com.loit.sample.model.request.SubjectRequest;


public interface LecturerService {

	public void save(Lecturer theLecturer);

	public List<Lecturer> findAll();

	public Lecturer getLecturerById(int id);

	public void deleteById(int id);

	public Lecturer updateLecturer(LecturerRequest lecturerRequest, int id);
	
	public LecturerSubject addSubjectToLecturer(int lecturerId, SubjectRequest subjectRequest);
	public void deleteSubjectFromLecturer(int lectureId,int subjectId);

}
