package com.karle.CRUDspringMySQL.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmployeeNotFound extends RuntimeException {

	//private String message;

	
	public EmployeeNotFound(String message) {
		super(message);
		//this.message = message;
	}

	public EmployeeNotFound(String message, Throwable cause) {
		super(message,cause);
		// TODO Auto-generated constructor stub
	}

	public EmployeeNotFound() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmployeeNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public EmployeeNotFound(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	
	
	
}
