package com.loit.sample.service;

import com.loit.sample.domain.LecturerSubject;

public interface LecturerSubjectService {
	public LecturerSubject addSubjectToLecture(int lecturerId, int subjectId);
	public String deleteLecturerSubject(int lecturerSubjectId);


}
