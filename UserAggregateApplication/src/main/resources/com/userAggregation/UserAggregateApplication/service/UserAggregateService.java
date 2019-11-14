package com.userAggregation.UserAggregateApplication.service;

import java.util.List;

import com.userAggregation.UserAggregateApplication.entity.JsendUser;
import com.userAggregation.UserAggregateApplication.entity.User;


public interface UserAggregateService {
	
	public List<JsendUser> findAll();
	
	public JsendUser findById(int theId);
	
	public JsendUser findByName(String name);
	
	public void saveUser(User theUser);

}
