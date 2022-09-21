package com.loit.sample.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.loit.sample.domain.Lecturer;

@Repository
public class LecturerDaoImpl implements LecturerDao{
	
	@Autowired
	private EntityManager entityManager;
	
	@Override
	public void save(Lecturer theLecturer) {
		entityManager.persist(theLecturer);
		
	}
	
	@Override
	public List<Lecturer> findAll() {
		try {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Lecturer> criteriaQuery = criteriaBuilder.createQuery(Lecturer.class);
		Root<Lecturer> LecturerRoot = criteriaQuery.from(Lecturer.class);
		
		criteriaQuery.orderBy(criteriaBuilder.asc(LecturerRoot.get("firstName")));
		List<Lecturer> lecturers = entityManager.createQuery(criteriaQuery).getResultList();
		return lecturers;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Lecturer getLecturerById(int id) {
		return entityManager.find(Lecturer.class, id);
	}

	@Override
	public void deleteById(Lecturer lecturer) {
		
		entityManager.remove(lecturer);
	}

	@Override
	public Lecturer updateLecturer(Lecturer lecturer) {
		lecturer = entityManager.merge(lecturer);
		return lecturer;
	}

}
