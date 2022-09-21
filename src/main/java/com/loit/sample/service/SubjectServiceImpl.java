package com.loit.sample.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loit.sample.dao.SubjectDao;

import com.loit.sample.domain.Subject;

import com.loit.sample.model.request.SubjectRequest;

@Service
public class SubjectServiceImpl implements SubjectService {

	@Autowired
	private SubjectDao subjectDao;

	@Override
	@Transactional
	public void save(Subject subject) {
		subjectDao.save(subject);

	}

	@Override
	@Transactional
	public List<Subject> findAll() {
		return subjectDao.findAll();
	}

	@Override
	@Transactional
	public Subject getSubjectById(int id) {
		return subjectDao.getSubjectById(id);
	}

	@Override
	@Transactional
	public void deleteById(int id) {

		Subject subject = subjectDao.getSubjectById(id);

		if (subject == null) {
			throw new RuntimeException("Subject id not found -" + id);
		} else {
			subjectDao.deleteById(subject);
			System.out.println("Deleted subject id - " + id);
		}
	}

	@Override
	@Transactional
	public Subject updateSubject(SubjectRequest subjectRequest, int id) {

		Subject subject = subjectDao.getSubjectById(id);

		subject.setSubjectName(subjectRequest.getSubjectName());
		subject.setSubjectDescription(subjectRequest.getSubjectDescription());

		return subjectDao.updateSubject(subject);

	}

}
