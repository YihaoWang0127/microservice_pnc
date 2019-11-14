package com.account.AccountApplication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@Column(name="id")
	private int id;
	
	//@Column(name="amount")
	private int amount;
	
	
	//@Column(name="user_id")
	private int userId;;
	
	public Account() {
		
	}

	public Account(int amount, int userId) {
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

	@Override
	public String toString() {
		return "Account [id=" + id + ", amount=" + amount + "]";
	}
	

}
