package com.loit.sample.enums;

public enum ApplicationError {
	COURSE_NOT_FOUND("001","Course does not Exsist"),
	COURSE_NOT_CREATED("002","Course not created\n All fields must be filled"),
	COURSES_NOT_FOUND("003","Courses not found"),
	COURSE_ALREADY_EXSIST("004","Course already Exsist"),
	STUDENT_ALREADY_EXSIST("005","Student already Exsist"),
	STUDENT_NOT_FOUND("006","Student does not Exsist"),
	STUDENT_NOT_CREATED("007","Student not created\n All fields must be filled"),
	STUDENTS_NOT_FOUND("008","Students not found"),
	LECTURER_NOT_FOUND("009","Lecturer does not Exsist"),
	LECTURER_NOT_CREATED("010","Lecturer not created\n All fields must be filled"),
	LECTURERS_NOT_FOUND("011","Lecturers not found"),
	LECTURER_ALREADY_EXSIST("012","Lecturer already Exsist"),
	SUBJECT_NOT_FOUND("013","Lecturer does not Exsist"),
	SUBJECT_NOT_CREATED("014","Lecturer not created\n All fields must be filled"),
	SUBJECTS_NOT_FOUND("015","Lecturers not found"),
	SUBJECT_ALREADY_EXSIST("016","Subject already Exsist"),
	NOT_FOUND("017","Url does not Found"),
	NOT_EXSIST("018","Url does not Exsist"),
	NOT_UPDATED("018","Url does not Updated");
	
	
	private String statusCode;
	private String statusMessage;
	
	
	private ApplicationError(String statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	

}
