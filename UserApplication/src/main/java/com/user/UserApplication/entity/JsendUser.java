package com.user.UserApplication.entity;

public class JsendUser {
	
	private String status;
	
	private String message;
	
	private User data;
	
	public JsendUser() {
		
	}

	public JsendUser(String status, String message, User data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public User getData() {
		return data;
	}

	public void setData(User data) {
		this.data = data;
	}
	
	
}
