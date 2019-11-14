package com.userAggregation.UserAggregateApplication.entity;

public class Account {
	
	private int id;
	
	private int amount;
	
	private int userId;
	
	public Account() {
		
	}

	public Account(int id, int amount, int userId) {
		this.id = id;
		this.amount = amount;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	

}
