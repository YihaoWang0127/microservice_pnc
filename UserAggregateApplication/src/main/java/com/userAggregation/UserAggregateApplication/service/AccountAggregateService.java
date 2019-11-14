package com.userAggregation.UserAggregateApplication.service;

import com.userAggregation.UserAggregateApplication.entity.Account;
import com.userAggregation.UserAggregateApplication.entity.JsendResponse;


public interface AccountAggregateService {
	
	public JsendResponse findByUserId(int theUserId);
	
	public void saveAccount(Account theAccount);

}
