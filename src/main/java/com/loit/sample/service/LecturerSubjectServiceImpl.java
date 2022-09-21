package com.loit.sample.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loit.sample.dao.LecturerDao;
import com.loit.sample.dao.LecturerSubjectDao;
import com.loit.sample.dao.SubjectDao;
import com.loit.sample.domain.LecturerSubject;

@Service
public class LecturerSubjectServiceImpl implements LecturerSubjectService{
	@Autowired
	private LecturerSubjectDao lecturerSubjectDao;
	@Autowired
	private LecturerDao lecturerDao;
	@Autowired
	private SubjectDao subjectDao;
	
	@Override
	@Transactional
	public LecturerSubject addSubjectToLecture(int lecturerId, int subjectId) {

		return null;
	}

	@Override
	public String deleteLecturerSubject(int lecturerSubjectId) {
		// TODO Auto-generated method stub
		return null;
	}


}
