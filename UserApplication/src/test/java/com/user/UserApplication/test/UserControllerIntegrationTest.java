package com.user.UserApplication.test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.user.UserApplication.UserApplication;
import com.user.UserApplication.dao.UserRepository;
import com.user.UserApplication.entity.User;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, 
	classes = UserApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
  locations = "classpath:application-test.properties")
public class UserControllerIntegrationTest {
	
	@Autowired
    private MockMvc mvc;
 
    @Autowired
    private UserRepository repository;
    
    @Test
    public void givenUsers_whenGetUsers_thenStatus200()
      throws Exception {
     
    	User user= new User("Alex", 15000, "alex@virtusa.com");
        
        User found = repository.save(user);
     
        mvc.perform(get("/api/users")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk())
          .andExpect(content()
          .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
          .andExpect(jsonPath("$[0].name", is("alex")))
          .andExpect(jsonPath("$[0].salary", is(15000)))
          .andExpect(jsonPath("$[0].email", is("alex@virtusa.com")));
    }

}
