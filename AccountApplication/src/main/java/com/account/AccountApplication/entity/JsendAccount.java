package com.account.AccountApplication.entity;

import java.util.List;

public class JsendAccount {

	private String status;
	
	private String message;
	
	private List<Account> data;
	
	public JsendAccount() {
		
	}

	public JsendAccount(String status, String message, List<Account> data) {
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

	public List<Account> getData() {
		return data;
	}

	public void setData(List<Account> data) {
		this.data = data;
	}
	
}
