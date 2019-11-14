package com.userAggregation.UserAggregateApplication.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userAggregation.UserAggregateApplication.entity.JsendUser;
import com.userAggregation.UserAggregateApplication.entity.User;

@Service
public class UserAggregateServiceImpl implements UserAggregateService {

	@Override
	public List<JsendUser> findAll() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<JsendUser>> responseEntity =
				restTemplate.exchange(
						"http://localhost:8080/api/users", 
						HttpMethod.GET,
						null, 
						new ParameterizedTypeReference<List<JsendUser>>() {}
						);
		
		List<JsendUser> theJsendUsers = responseEntity.getBody();
		
		return theJsendUsers;
	}

	@Override
	public JsendUser findById(int theId) {
		
		ResponseEntity<JsendUser> responseEntity =
				new RestTemplate().getForEntity(
						"http://localhost:8080/api/user/{UserId}",
						JsendUser.class, 
						theId
						);
		
		JsendUser theUser = responseEntity.getBody();
		
		return theUser;
	}

	@Override
	public JsendUser findByName(String name) {

		ResponseEntity<JsendUser> responseEntity =
				new RestTemplate().getForEntity(
						"http://localhost:8080/api/username/{name}",
						JsendUser.class, 
						name
						);
		
		JsendUser theJsendUser = responseEntity.getBody();
		
		return theJsendUser;
		
	}

	@Override
	public void saveUser(User theUser) {

		HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        HttpEntity<User> requestEntity = new HttpEntity<>(theUser, requestHeaders);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<User> responseEntity = restTemplate.exchange(
				"http://localhost:8080/api/users",
                HttpMethod.POST,
                requestEntity,
                User.class
                );
		
	}

	
}
