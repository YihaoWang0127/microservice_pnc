package com.userAggregation.UserAggregateApplication.rest;


public class UserAggregateNotFoundException extends RuntimeException {
	
	public UserAggregateNotFoundException() {
		
	}

	public UserAggregateNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserAggregateNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserAggregateNotFoundException(String message) {
		super(message);
	}

	public UserAggregateNotFoundException(Throwable cause) {
		super(cause);
	}

}
