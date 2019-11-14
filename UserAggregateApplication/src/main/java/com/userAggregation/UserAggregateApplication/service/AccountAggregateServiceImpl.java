package com.userAggregation.UserAggregateApplication.service;

import java.util.Arrays;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userAggregation.UserAggregateApplication.entity.Account;
import com.userAggregation.UserAggregateApplication.entity.JsendResponse;

@Service
public class AccountAggregateServiceImpl implements AccountAggregateService {

	@Override
	public JsendResponse findByUserId(int theUserId) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<JsendResponse> responseEntity =
				restTemplate.exchange(
						"http://localhost:8081/api/account/{theUserId}", 
						HttpMethod.GET,
						null, 
						new ParameterizedTypeReference<JsendResponse>() {}, 
						String.valueOf(theUserId)
						);
		
		JsendResponse theUserResponse = responseEntity.getBody();
		
		return theUserResponse;
		
	}

	@Override
	public void saveAccount(Account theAccount) {
		
		HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        HttpEntity<Account> requestEntity = new HttpEntity<>(theAccount, requestHeaders);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Account> responseEntity = restTemplate.exchange(
				"http://localhost:8081/api/accounts",
                HttpMethod.POST,
                requestEntity,
                Account.class
                );
		
		

	}

}
