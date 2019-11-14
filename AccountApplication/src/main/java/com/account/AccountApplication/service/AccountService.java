package com.account.AccountApplication.service;

import java.util.List;

import com.account.AccountApplication.entity.Account;

public interface AccountService {
	
	public List<Account> findAll();
	
	public List<Account> findByUserId(int theUserId);
	
	public void saveAccount(Account theAccount);

}
