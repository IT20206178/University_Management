package com.loit.sample.exception;

import com.loit.sample.enums.ApplicationError;

public class ApplicationException extends RuntimeException{

	private String code;
	private String message;
	
	
	public ApplicationException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	
	public ApplicationException(ApplicationError applicationError) {
		this.code =  applicationError.getStatusCode();
		this.message = applicationError.getStatusMessage();
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
