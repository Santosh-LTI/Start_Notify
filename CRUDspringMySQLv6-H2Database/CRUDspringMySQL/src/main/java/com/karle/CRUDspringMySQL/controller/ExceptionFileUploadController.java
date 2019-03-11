package com.karle.CRUDspringMySQL.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.karle.CRUDspringMySQL.model.ErrorResponse;

@ControllerAdvice
public class ExceptionFileUploadController {

	 @ExceptionHandler(Exception.class)
	    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
	        ErrorResponse error = new ErrorResponse();
	        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	        error.setMessage("Please contact your administrator");
	        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	    }
	 
	 
	 @ExceptionHandler(RuntimeException.class)
	    public ResponseEntity<ErrorResponse> exceptionHandler(RuntimeException ex) {
	        ErrorResponse error = new ErrorResponse();
	        error.setErrorCode(HttpStatus.NOT_FOUND.value());
	        error.setMessage("Employee Not Found!");
	        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	    }
	 
}
