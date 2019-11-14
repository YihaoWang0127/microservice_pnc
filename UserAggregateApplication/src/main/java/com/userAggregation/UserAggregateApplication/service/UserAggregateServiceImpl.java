package com.userAggregation.UserAggregateApplication.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userAggregation.UserAggregateApplication.entity.User;
import com.userAggregation.UserAggregateApplication.entity.JsendResponse;

@Service
public class UserAggregateServiceImpl implements UserAggregateService {
	
	private ObjectMapper mapper = new ObjectMapper();//the mapper will help to do generic in user class

	@Override
	public List<JsendResponse> findAll() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<JsendResponse>> responseEntity =
				restTemplate.exchange(
						"http://localhost:8080/api/users", 
						HttpMethod.GET,
						null, 
						new ParameterizedTypeReference<List<JsendResponse>>() {}
						);
		
		List<JsendResponse> theUserResponse = responseEntity.getBody();
		
		
		return theUserResponse;
	}

	@Override
	public JsendResponse findById(int theId) {
		
		ResponseEntity<JsendResponse> responseEntity =
				new RestTemplate().getForEntity(
						"http://localhost:8080/api/user/{UserId}",
						JsendResponse.class, 
						theId
						);
		
		JsendResponse theUser = responseEntity.getBody();
		
		return theUser;
	}

	@Override
	public JsendResponse findByName(String name) {

		ResponseEntity<JsendResponse> responseEntity =
				new RestTemplate().getForEntity(
						"http://localhost:8080/api/username/{name}",
						JsendResponse.class, 
						name
						);
		
		JsendResponse theUserResponse = responseEntity.getBody();
		
		return theUserResponse;
		
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
