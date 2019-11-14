package com.account.AccountApplication.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.account.AccountApplication.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {
	
	@Query("select a from Account a where a.userId= ?1")
	List<Account> findByUserId(int theUserId);

}
