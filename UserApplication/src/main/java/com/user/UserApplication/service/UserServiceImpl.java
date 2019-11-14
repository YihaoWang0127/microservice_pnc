package com.user.UserApplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.UserApplication.dao.UserRepository;
import com.user.UserApplication.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	/*
	@Autowired
	public UserServiceImpl(UserRepository theUserRepository) {
		userRepository = theUserRepository;
	}
	*/
	
	@Override
	public List<User> findAll() {
		
		return userRepository.findAll();
		
	}

	@Override
	public User findById(int theId) {
		
		Optional<User> result = userRepository.findById(theId);
		
		User theUser = null;
		
		if(result.isPresent()) {
			theUser = result.get();
		}else {
			//throw new RuntimeException("Did not find the id - " + theId);
			theUser = new User();
		}
		
		return theUser;
		
	}

	@Override
	public User findByName(String name) {
		if(userRepository.findByName(name) == null) {
			return new User();
		}
		return userRepository.findByName(name);
	}
	
	@Override
	public void saveUser(User theUser) {
		
		userRepository.saveAndFlush(theUser);
		
	}

}
