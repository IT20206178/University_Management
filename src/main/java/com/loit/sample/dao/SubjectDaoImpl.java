package com.loit.sample.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.loit.sample.domain.Subject;
import com.loit.sample.model.request.SubjectRequest;

@Repository
public class SubjectDaoImpl implements SubjectDao{
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public Subject save(Subject subject) {
		entityManager.persist(subject);
		return subject;
	}
	
	@Override
	public List<Subject> findAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Subject> criteriaQuery = criteriaBuilder.createQuery(Subject.class);
		Root<Subject> subjectRoot = criteriaQuery.from(Subject.class);
		
		criteriaQuery.orderBy(criteriaBuilder.asc(subjectRoot.get("subjectName")));
		List<Subject> subjects = entityManager.createQuery(criteriaQuery).getResultList();
		return subjects;
		
	}
	
	@Override
	public Subject getSubjectById(int id) {
		return entityManager.find(Subject.class, id);
	}
	


	@Override
	public void deleteById(Subject subject) {
		
		entityManager.remove(subject);
	}

	@Override
	public Subject updateSubject(Subject subject) {
		subject = entityManager.merge(subject);
		return subject;
	}

	

}
