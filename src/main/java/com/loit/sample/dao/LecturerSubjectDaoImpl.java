package com.loit.sample.dao;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.loit.sample.domain.LecturerSubject;

@Repository
public class LecturerSubjectDaoImpl implements LecturerSubjectDao{

	@Autowired
	private EntityManager entityManager;

	@Override
	public LecturerSubject saveSubjectToLecturer(LecturerSubject lecturerSubject) {
		entityManager.persist(lecturerSubject);
		return lecturerSubject;
	}


	@Override
	public LecturerSubject getSubjectByLectureId(int id) {
		
		return entityManager.find(LecturerSubject.class, id);
	}

	@Override
	public String deleteByLecturerSubjectId(int id) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaDelete<LecturerSubject> criteriaDelete = criteriaBuilder.createCriteriaDelete(LecturerSubject.class);
		Root<LecturerSubject> lecturerSubjectRoot = criteriaDelete.from(LecturerSubject.class);
		criteriaDelete.where(lecturerSubjectRoot.get("id").in(id));
		entityManager.createQuery(criteriaDelete).executeUpdate();
		return "Record deleted";
		
	}


	@Override
	public void deleteLecturerSubject(LecturerSubject lecturerSubject) {
		
		entityManager.remove(lecturerSubject);
		
	}


	@Override
	public LecturerSubject getLecturerSubject(int lecturerId, int subjectId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<LecturerSubject> query = cb.createQuery(LecturerSubject.class);
        Root<LecturerSubject> root = query.from(LecturerSubject.class);

        query.select(root).where(cb.and(cb.equal(root.get("lecturer"),lecturerId ) , cb.equal(root.get("subject"), subjectId)));

        return entityManager.createQuery(query).getSingleResult();
	}


}
