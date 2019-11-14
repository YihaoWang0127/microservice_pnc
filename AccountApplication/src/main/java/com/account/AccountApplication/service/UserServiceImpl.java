package com.account.AccountApplication.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.account.AccountApplication.entity.JsendUser;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public JsendUser findById(int theId) {
		
		ResponseEntity<JsendUser> responseEntity =
				new RestTemplate().getForEntity(
						"http://localhost:8080/api/user/{UserId}",
						JsendUser.class, 
						theId
						);
		
		JsendUser theJsendUser = responseEntity.getBody();
		
		return theJsendUser;
	}

}
