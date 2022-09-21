package com.loit.sample.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Value(value = "${data.exception.message1}")
	private String message1;
	@Value(value = "${data.exception.message2}")
	private String message2;

	@ExceptionHandler(value = CourseNotFoundException.class)
	public ResponseEntity blogNotFoundException(CourseNotFoundException courseNotFoundException) {
		return new ResponseEntity(message2, HttpStatus.NOT_FOUND);
	}

	
}
