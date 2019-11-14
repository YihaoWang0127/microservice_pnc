package com.user.UserApplication.service;

import java.util.List;

import com.user.UserApplication.entity.User;

public interface UserService {
	
	public List<User> findAll();
	
	public User findById(int theId);
	
	public User findByName(String name);

	public void saveUser(User theUser);

}
