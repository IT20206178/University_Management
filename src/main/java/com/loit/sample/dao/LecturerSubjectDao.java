package com.loit.sample.dao;

import com.loit.sample.domain.LecturerSubject;

public interface LecturerSubjectDao {
	public LecturerSubject saveSubjectToLecturer(LecturerSubject lecturerSubject);
	public LecturerSubject getSubjectByLectureId(int id);
	public String deleteByLecturerSubjectId(int id);
	public void deleteLecturerSubject(LecturerSubject lecturerSubject);
	public LecturerSubject getLecturerSubject(int lecturerId,int subjectId);
	

}
