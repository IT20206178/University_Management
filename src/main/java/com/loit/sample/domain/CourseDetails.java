package com.loit.sample.domain;

import javax.persistence.*;



import java.util.Date;
import java.util.Set;


@Entity

@Table(name="course")

public class CourseDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "course_name")
	private String courseName;
	
	@Column(name="course_description")
	private String courseDescription;
	
	@Column(name="course_fee")
	private double  courseFee;
	
	@Column(name="course_duration")
	private int courseDuration;
	
	@Column(name="course_start_date")
	private Date startDate; 
	
	@Column(name="course_end_date")
	private Date endDate;
	

	@ManyToMany(mappedBy = "courses")
	private Set <Student> students;
	
	 @ManyToMany
		@JoinTable(
		  name = "course_subject",
		  joinColumns = @JoinColumn(name = "course_id"),
		  inverseJoinColumns = @JoinColumn(name = "subject_id"))
	    private Set<Subject> subjects;
	
	
//	public CourseDetails(int id, String courseName, String courseDescription, double courseFee, int courseDuration, Date startDate, Date endDate) {
//		this.id=id;
//		this.courseName=courseName;
//		this.courseDescription = courseDescription;
//		this.courseFee=courseFee;
//		this.courseDuration=courseDuration;
//		this.startDate=startDate;
//		this.endDate=endDate;
//	}
//	
//	public CourseDetails() {
//
//    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getCourseName() {
		return courseName;
	}


	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}


	public String getCourseDescription() {
		return courseDescription;
	}


	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}


	public double getCourseFee() {
		return courseFee;
	}


	public void setCourseFee(double courseFee) {
		this.courseFee = courseFee;
	}


	public int getCourseDuration() {
		return courseDuration;
	}


	public void setCourseDuration(int courseDuration) {
		this.courseDuration = courseDuration;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
	
	 public void addSubject(Subject subject) {
		 this.subjects.add(subject);//get the existing subjects list and add new one
		 subject.getCourses().add(this);
	 }
	 
	 public void removeSubject(Subject subject) {
		 this.subjects.remove(subject);
		 subject.getCourses().remove(this);
	 }
	
	
}
