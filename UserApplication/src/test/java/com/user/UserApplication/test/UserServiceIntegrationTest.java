package com.user.UserApplication.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.user.UserApplication.dao.UserRepository;
import com.user.UserApplication.entity.User;
import com.user.UserApplication.service.UserService;
import com.user.UserApplication.service.UserServiceImpl;


@RunWith(SpringRunner.class)
public class UserServiceIntegrationTest {
	
	@TestConfiguration
    static class UserServiceImplTestContextConfiguration {
  
        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }
 
    @Autowired
    private UserService userService;
 
    @MockBean
    private UserRepository userRepository;
    
    @Before
    public void setUp() {
        User alex = new User("alex", 15000, "alex@virtusa.com");
     
        Mockito.when(userRepository.findByName(alex.getName()))
          .thenReturn(alex);
    }
    
    @Test
    public void whenValidName_thenUserShouldBeFound() {
        String name = "alex", email="alex@virtusa.com";
        int salary = 15000;
        User found = userService.findByName(name);
      
         assertThat(found.getName())
          .isEqualTo(name);
         
         assertThat(found.getSalary())
         .isEqualTo(salary);
         
         assertThat(found.getEmail())
         .isEqualTo(email);
     }

}
