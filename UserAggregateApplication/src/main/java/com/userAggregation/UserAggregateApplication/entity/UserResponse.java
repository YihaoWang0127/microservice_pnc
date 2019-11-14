package com.userAggregation.UserAggregateApplication.entity;

import java.util.List;

public class UserResponse {
	
    private int id;
	
	private String name;
	
	private int salary;
	
	private String email;
	
	private List<Account> account;
	
	public UserResponse() {
		
	}

	public UserResponse(int id, String name, int salary, String email, List<Account> account) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.email = email;
		this.account = account;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

	
}
