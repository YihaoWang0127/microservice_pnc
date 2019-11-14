package com.account.AccountApplication.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.account.AccountApplication.dao.AccountRepository;
import com.account.AccountApplication.entity.Account;
import com.account.AccountApplication.service.AccountService;
import com.account.AccountApplication.service.AccountServiceImpl;


@RunWith(SpringRunner.class)
public class AccountServiceIntegrationTest {
	
	@TestConfiguration
    static class UserServiceImplTestContextConfiguration {
  
        @Bean
        public AccountService userService() {
            return new AccountServiceImpl();
        }
    }
 
    @Autowired
    private AccountService accountService;
 
    @MockBean
    private AccountRepository accountRepository;
    
    @Before
    public void setUp() {
        List<Account> alex = new LinkedList<>();
        alex.add(new Account(15000,2));
     
        Mockito.when(accountRepository.findByUserId(alex.get(0).getUserId()))
          .thenReturn(alex);
    }
    
    @Test
    public void whenValidId_thenAccountShouldBeFound() {
        int amount= 15000, theUserId = 2;
        Account found = accountService.findByUserId(theUserId).get(0);
      
         assertThat(found.getAmount())
          .isEqualTo(amount);
         
         assertThat(found.getUserId())
         .isEqualTo(theUserId);
         
     }

}
