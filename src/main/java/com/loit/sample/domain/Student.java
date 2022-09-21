package com.loit.sample.domain;

import java.util.Date;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;






@Entity

@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone_number")
	private int phoneNumber;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "nic")
	private String nic;
	
	@Column(name = "gender")
	private String gender;
	
	@Column(name = "dob")
	private Date dob;
	
	@Column(name = "school")
	private String school;
	
	@Column(name = "start_date")
	private Date startDate;
	
	
	@ManyToMany
	@JoinTable(name="course_student", joinColumns=@JoinColumn(name="student_id"), 
	inverseJoinColumns = @JoinColumn(name="course_id"))
	private Set<CourseDetails> courses;

//	public Student(int id, String firstName, String lastName, String address, int phoneNumber, String email,
//			String nic, String gender, Date dob, String school, Date startDate) {
//		
//		this.id = id;
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.address = address;
//		this.phoneNumber = phoneNumber;
//		this.email = email;
//		this.nic = nic;
//		this.gender = gender;
//		this.dob = dob;
//		this.school = school;
//		this.startDate = startDate;
//	}
//	
//	
//	public Student() {
//		
//	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNic() {
		return nic;
	}


	public void setNic(String nic) {
		this.nic = nic;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public String getSchool() {
		return school;
	}


	public void setSchool(String school) {
		this.school = school;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Set< CourseDetails> getCourses(){
		return courses;
	}
	
	public void setCourses(Set<CourseDetails> courses) {
		this.courses = courses;
	}

	public void addCourses(CourseDetails courseDetails) {
		this.courses.add(courseDetails);
		courseDetails.getStudents().add(this);
	}

	public void removeCourse(CourseDetails courseDetails) {
		this.courses.remove(courseDetails);
		courseDetails.getStudents().remove(this);

	}

}
