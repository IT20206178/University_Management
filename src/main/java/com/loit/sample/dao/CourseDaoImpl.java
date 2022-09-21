package com.loit.sample.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.loit.sample.domain.CourseDetails;

@Repository
public class CourseDaoImpl implements CourseDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public CourseDetails save(CourseDetails courseDetails) {

		entityManager.persist(courseDetails);
		return courseDetails;
	}

	@Override
	public List<CourseDetails> findAll() {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<CourseDetails> criteriaQuery = criteriaBuilder.createQuery(CourseDetails.class);
			Root<CourseDetails> courseRoot = criteriaQuery.from(CourseDetails.class);

			criteriaQuery.orderBy(criteriaBuilder.desc(courseRoot.get("courseName")));
			List<CourseDetails> courses = entityManager.createQuery(criteriaQuery).getResultList();
			return courses;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CourseDetails getCourseById(int id) {
		try {
			return entityManager.find(CourseDetails.class, id);
		} catch (Exception e) {
			return null;

		}
	}

	@Override
	public void deleteById(CourseDetails courseDetails) {
		entityManager.remove(courseDetails);
	}

	@Override
	public CourseDetails updateCourse(CourseDetails courseDetails) {
		courseDetails = entityManager.merge(courseDetails);
		return courseDetails;
	}

	@Override
	public CourseDetails getCourseByName(String name) {
		try {
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			CriteriaQuery<CourseDetails> criteriaQuery = criteriaBuilder.createQuery(CourseDetails.class);
			Root<CourseDetails> courseRoot = criteriaQuery.from(CourseDetails.class);
			criteriaQuery.where(criteriaBuilder.equal(courseRoot.get("courseName"), name));
			TypedQuery<CourseDetails> typedQuery = entityManager.createQuery(criteriaQuery);
			CourseDetails response = typedQuery.getSingleResult();
			return response;
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Already exsisting");
			return null;

		}
	}
}
