package com.loit.sample.exception;


public class CourseAlreadyExistsException extends RuntimeException{

	 private String message;
	    public CourseAlreadyExistsException(String message) {
	        super(message);
	        this.message = message;
	    }
	    public CourseAlreadyExistsException() {
	    }
}
