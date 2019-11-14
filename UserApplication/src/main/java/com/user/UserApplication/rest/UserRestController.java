package com.user.UserApplication.rest;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.UserApplication.entity.JsendUser;
import com.user.UserApplication.entity.User;
import com.user.UserApplication.service.UserService;


@RestController
@RequestMapping("/api")
public class UserRestController {
	
	private UserService userService;
	
	@Autowired
	public UserRestController(UserService theUserService) {
		userService = theUserService;
	}
	
	@GetMapping("/users")
	public List<JsendUser> findAll(){
		
		List<User> users = userService.findAll();
		
		List<JsendUser> jsendUsers = new LinkedList<>();
		
		for(User u:users) {
			
			JsendUser juser = new JsendUser("Success","The user is found", u);
			
			jsendUsers.add(juser);
		
		}
		
		return jsendUsers;
	}
	
	@GetMapping("/user/{userId}")
	public JsendUser findById(@PathVariable int userId) {
		
		User theUser = userService.findById(userId);
		
		JsendUser theJsendUser = new JsendUser();
		
		if(theUser.getName()==null) {
			//throw new UserNotFoundException("User id not found - " + userId);
			theUser = new User();
			theJsendUser.setStatus("Error");
			theJsendUser.setMessage("The user with ID is not found: " + userId);
			theJsendUser.setData(theUser);
		}else {
			theJsendUser = new JsendUser("Success", "The user with ID is found: " + userId, theUser);
		}
		
		return theJsendUser;
		
	}
	
	@GetMapping("/username/{name}")
	public JsendUser findByName(@PathVariable String name) {
		User theUser = userService.findByName(name);
		
		JsendUser theJsendUser = new JsendUser();
		
		if(theUser == null) {
			//throw new UserNotFoundException("User id not found - " + name);
			theUser = new User();
			theJsendUser.setStatus("Error");
			theJsendUser.setMessage("The user with name is not found: " + name);
			theJsendUser.setData(theUser);
		}else {
			theJsendUser = new JsendUser("Success", "The user with name is found: " + name, theUser);
		}
		
		return theJsendUser;
	}
	
	@PostMapping("/users")
	public JsendUser updateUser(@RequestBody User theUser) {
		
		User user = userService.findByName(theUser.getName());
		
		JsendUser theJsendUser = new JsendUser();
		
		if(user.getName()!=null) {
			
			theJsendUser.setStatus("Fail");
			theJsendUser.setMessage("The user already exists");
			theJsendUser.setData(user);
			
		}else{
			theJsendUser.setStatus("Success");
			theJsendUser.setMessage("The user has been added successfully");
			theJsendUser.setData(theUser);
			userService.saveUser(theUser);
		}
		
		return theJsendUser;
	}

}
