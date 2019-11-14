package com.account.AccountApplication.test;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.account.AccountApplication.AccountApplication;
import com.account.AccountApplication.dao.AccountRepository;
import com.account.AccountApplication.entity.Account;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, 
	classes = AccountApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application-test.properties")
public class AccountControllerIntegrationTest {
	
	@Autowired
    private MockMvc mvc;
 
    @Autowired
    private AccountRepository repository;
    
    @Test
    public void givenUsers_whenGetUsers_thenStatus200()
      throws Exception {
     
    	Account account = new Account(15000, 2);
    	
    	int theUserId = account.getUserId();
        
        Account found = repository.save(account);
     
        mvc.perform(get("/api/account/{theUserId}", theUserId)
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(content()
          .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$[0].amount", is(15000)))
          .andExpect(jsonPath("$[0].userId", is(2)));
        
    }

}
