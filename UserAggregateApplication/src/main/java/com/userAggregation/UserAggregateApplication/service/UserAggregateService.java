package com.userAggregation.UserAggregateApplication.service;

import java.util.List;

import com.userAggregation.UserAggregateApplication.entity.User;
import com.userAggregation.UserAggregateApplication.entity.JsendResponse;


public interface UserAggregateService {
	
	public List<JsendResponse> findAll();
	
	public JsendResponse findById(int theId);
	
	public JsendResponse findByName(String name);
	
	public void saveUser(User theUser);

}
