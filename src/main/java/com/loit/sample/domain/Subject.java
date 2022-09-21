package com.loit.sample.domain;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "subject")
public class Subject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "subject_name")
	private String subjectName;

	@Column(name = "subject_description")
	private String subjectDescription;

	@ManyToMany(mappedBy = "subjects")
	private Set<CourseDetails> courses;
	
	@OneToMany(mappedBy = "subject")
	private Set<LecturerSubject> lecturerSubjects;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectDescription() {
		return subjectDescription;
	}

	public void setSubjectDescription(String subjectDescription) {
		this.subjectDescription = subjectDescription;
	}

	public Set<CourseDetails> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseDetails> courses) {
		this.courses = courses;
	}
	
	public Set<LecturerSubject> getLecturerSubjects() {
		return lecturerSubjects;
	}

	public void setLecturerSubjects(Set<LecturerSubject> lecturerSubjects) {
		this.lecturerSubjects = lecturerSubjects;
	}

}
