package com.account.AccountApplication.entity;

public class User {

    private int id;
	
	private String name;
	
	private int salary;
	
	private String email;
	
	public User() {
		
	}

	public User(int id, String name, int salary, String email) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.email = email;
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
	
}
