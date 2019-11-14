package com.account.AccountApplication.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.AccountApplication.dao.AccountRepository;
import com.account.AccountApplication.entity.Account;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	/*
	@Autowired
	public AccountServiceImpl(AccountRepository theAccountRepository) {
		accountRepository = theAccountRepository;
	}
	*/

	@Override
	public List<Account> findByUserId(int theUserId) {
		return accountRepository.findByUserId(theUserId);
	}

	@Override
	public void saveAccount(Account theAccount) {

		accountRepository.saveAndFlush(theAccount);
		
	}

	@Override
	public List<Account> findAll() {
		
		return accountRepository.findAll();
		
	}

}
