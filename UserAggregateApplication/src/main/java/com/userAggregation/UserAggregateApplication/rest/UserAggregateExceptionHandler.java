package com.userAggregation.UserAggregateApplication.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class UserAggregateExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<UserAggregateErrorResponse> handleException(UserAggregateNotFoundException exc) {
		
		// create CustomerErrorResponse
		
		UserAggregateErrorResponse error = new UserAggregateErrorResponse(
											HttpStatus.NOT_FOUND.value(),
											exc.getMessage(),
											System.currentTimeMillis());
		
		// return ResponseEntity
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	
	// Add another exception handler ... to catch any exception (catch all)

	@ExceptionHandler
	public ResponseEntity<UserAggregateErrorResponse> handleException(BadRequestException exc) {
		
		// create CustomerErrorResponse
		
		UserAggregateErrorResponse error = new UserAggregateErrorResponse(
											HttpStatus.BAD_REQUEST.value(),
											exc.getMessage(),
											System.currentTimeMillis());
		
		// return ResponseEntity
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
