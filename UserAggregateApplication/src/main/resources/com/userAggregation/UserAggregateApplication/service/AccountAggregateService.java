package com.userAggregation.UserAggregateApplication.service;

import com.userAggregation.UserAggregateApplication.entity.Account;
import com.userAggregation.UserAggregateApplication.entity.JsendAccount;


public interface AccountAggregateService {
	
	public JsendAccount findByUserId(int theUserId);
	
	public void saveAccount(Account theAccount);

}
