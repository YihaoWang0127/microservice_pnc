package com.userAggregation.UserAggregateApplication.rest;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userAggregation.UserAggregateApplication.entity.Account;
import com.userAggregation.UserAggregateApplication.entity.JsendResponse;
import com.userAggregation.UserAggregateApplication.entity.User;
import com.userAggregation.UserAggregateApplication.entity.UserResponse;
import com.userAggregation.UserAggregateApplication.service.AccountAggregateService;
import com.userAggregation.UserAggregateApplication.service.UserAggregateService;

@RestController
@RequestMapping("/client")
public class UserAggregateRestController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ObjectMapper mapper = new ObjectMapper();//the mapper will help to do generic in user class
	
	@Autowired
	private UserAggregateService userService;
	
	@Autowired
	private AccountAggregateService accountService;
	
	
	@GetMapping("/users")
	public JsendResponse findAll(){
		
		List<JsendResponse> theJsendResponse = userService.findAll();
		
		List<UserResponse> theUserResponse= new LinkedList<>();
		
		JsendResponse theResponse = new JsendResponse();
						
		if(theJsendResponse==null||theJsendResponse.size()==0) {
			
			throw new UserAggregateNotFoundException("No user exists");
			
		}
		
		for(JsendResponse u:theJsendResponse) {
			
			User theUser = mapper.convertValue(u.getData(), User.class);
			
			List<Account> accounts = 
					mapper.convertValue(accountService.findByUserId(theUser.getId()).getData(), List.class);
			
			if(accounts == null || accounts.size()==0) {
				
				theUserResponse.add(new UserResponse(theUser.getId(), 
						theUser.getName(),theUser.getSalary(), theUser.getEmail(), null));
				
			}else {
				
				theUserResponse.add(new UserResponse(theUser.getId(), 
						theUser.getName(),theUser.getSalary(), theUser.getEmail(), accounts));
				
			}
				
		}
		
		theResponse = new JsendResponse("Success", "The users are listed below", theUserResponse);
		
		return theResponse;
		
	}
	
	@GetMapping("/user/{userId}")
	public JsendResponse findById(@PathVariable int userId) {
		
		if(userId <= 0) {
			
			throw new BadRequestException("Please provide valid Id Number");
			
		}
		
		User u = mapper.convertValue(userService.findById(userId).getData(), User.class);
		
		List<Account> accounts = 
				mapper.convertValue(accountService.findByUserId(userId).getData(), List.class);
		
		JsendResponse theJsendUserResponse = new JsendResponse();
		
		UserResponse theUserResponse = new UserResponse();
		
		if(u.getName()==null) {
			
			throw new UserAggregateNotFoundException("User id not found - " + userId);
			
		}else {
			
			theUserResponse = new UserResponse(u.getId(), 
					u.getName(),u.getSalary(), u.getEmail(), accounts);
			
			theJsendUserResponse = 
					new JsendResponse("Success","The user is found with ID: " + userId, theUserResponse);
			
		}
		
		return theJsendUserResponse;
		
	}
	
	@GetMapping("/username/{name}")
	public JsendResponse findByName(@PathVariable String name) {
		
		User u = mapper.convertValue(userService.findByName(name).getData(), User.class);
		
		List<Account> accounts = 
				mapper.convertValue(accountService.findByUserId(u.getId()).getData(), List.class);
		
		JsendResponse theJsendUserResponse = new JsendResponse();
		
		UserResponse theUserResponse = new UserResponse();
		
		if(u.getName() == null) {
			
			throw new UserAggregateNotFoundException("User Name not found - " + name);
			
		}
		else {
			
			theUserResponse = new UserResponse(u.getId(), 
					u.getName(),u.getSalary(), u.getEmail(), accounts);
			
			theJsendUserResponse = 
					new JsendResponse("Success", "The user is found with Name: " + name, theUserResponse);
			
		}
		
		return theJsendUserResponse;
		
	}
	
	@PostMapping("/accounts")
	public JsendResponse updateUser(@RequestBody Account theAccount) {
		
		if(theAccount.getAmount()<=0 || theAccount.getUserId()<=0) {
			throw new BadRequestException("Please provide valid or complete information");
		}
		
		User u = mapper.convertValue(userService.findById(theAccount.getUserId()).getData(), User.class);
		
		JsendResponse theJsendUserResponse = new JsendResponse();
		
		UserResponse theUserResponse = new UserResponse();
				
		if(u.getName()==null) {
			
			throw new BadRequestException("The user you want to add account to doesn't exist ");
			
		}
		
		accountService.saveAccount(theAccount);
		
		List<Account> accounts = 
				mapper.convertValue(accountService.findByUserId(theAccount.getUserId()).getData(), List.class);
		
		theUserResponse = new UserResponse(u.getId(), 
				u.getName(),u.getSalary(), u.getEmail(), accounts);
		
		theJsendUserResponse = 
				new JsendResponse("Success", "The account has been added successfully", theUserResponse);
		
		return theJsendUserResponse;
		
	}
	
	@PostMapping("/users")
	public JsendResponse saveUser(@RequestBody User theUser) {
		
		if(theUser.getName()==null || theUser.getName().length()==0
				|| theUser.getSalary()<=0 ||
				theUser.getEmail()==null || theUser.getEmail().length()==0) {
			throw new BadRequestException("Please provide valid or complete information");
		}
		
		List<Account> accounts = new LinkedList<>();
		
		JsendResponse theJsendUserResponse = new JsendResponse();
		
		UserResponse theUserResponse = new UserResponse();
		
		User u = mapper.convertValue( userService.findByName(theUser.getName()).getData(), User.class);
		
		if(u.getName()!=null) {
			
			throw new BadRequestException("The user already exists ");

		}
		
		userService.saveUser(theUser);
		
		u = mapper.convertValue( userService.findByName(theUser.getName()).getData(), User.class);
		
		theUserResponse = new UserResponse(u.getId(), 
				u.getName(),u.getSalary(), u.getEmail(), accounts);
				
		theJsendUserResponse =
				new JsendResponse("Success", "The user has been added successfully", theUserResponse);
		
		return theJsendUserResponse;
		
	}

}
