package com.user.UserApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.user.UserApplication.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("select u from User u where u.name = ?1")
	  User findByName(String name);

}
