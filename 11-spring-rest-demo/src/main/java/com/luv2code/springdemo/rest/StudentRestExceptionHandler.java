package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

	
	//add exception handling code here
	
	// Add an exception handler using @ExceptionHandler
	
		@ExceptionHandler
		public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException e){
			
			// create student response
			StudentErrorResponse error = new StudentErrorResponse();
			error.setStatus(HttpStatus.NOT_FOUND.value());
			error.setMessage(e.getMessage());
			error.setTimeStamp(System.currentTimeMillis());
			
			// return Response Entity
			
			return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		}
		
		//Add another exception handler .. to catch any exception
		
		@ExceptionHandler
		public ResponseEntity<StudentErrorResponse> handleException(Exception e){

			//create response
			StudentErrorResponse error = new StudentErrorResponse();
			error.setStatus(HttpStatus.BAD_REQUEST.value());
			error.setMessage("Input mismatch, (int) input is demanded");
			error.setTimeStamp(System.currentTimeMillis());
			
			// return Response Entity
			
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
}
