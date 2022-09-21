package com.loit.sample.dao;

import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.loit.sample.domain.Student;
import com.loit.sample.model.response.StudentResponse;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	private EntityManager entityManager;

	public void save(Student theStudent) {
		entityManager.persist(theStudent);
	}

	@Override
	public List<Student> findAll() {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
			Root<Student> courseRoot = criteriaQuery.from(Student.class);

			criteriaQuery.orderBy(criteriaBuilder.desc(courseRoot.get("firstName")));
			List<Student> students = entityManager.createQuery(criteriaQuery).getResultList();
			return students;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Student getStudentById(int id) {
		return entityManager.find(Student.class, id);
	}

	@Override
	public List<Student> searchStudent(String firstName, String lastName, String nic) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Student> query = cb.createQuery(Student.class);
		Root<Student> from = query.from(Student.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(firstName!=null && !firstName.equals("")) {
			Expression<String> expression=from.get("firstName");
			Predicate predicate = cb.and(cb.like(expression,firstName + "%"));
			predicates.add(predicate);
		}
		
		if(lastName!=null && !lastName.equals("")) {
			Expression<String> expression=from.get("lastName");
			Predicate predicate = cb.and(cb.like(expression,lastName + "%"));
			predicates.add(predicate);
		}
		
		if(nic!=null && !nic.equals("")) {
			Expression<String> expression=from.get("nic");
			Predicate predicate = cb.and(cb.like(expression,nic + "%"));
			predicates.add(predicate);
		}
		
		query.select(from).where(predicates.toArray(new Predicate[predicates.size()]));
		

		return entityManager.createQuery(query).getResultList();

	}

	@Override
	public void deleteById(Student student) {
		entityManager.remove(student);
	}

	@Override
	public Student updateStudent(Student student) {
		student = entityManager.merge(student);
		return student;
	}

}
