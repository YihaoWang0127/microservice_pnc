package com.account.AccountApplication.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.account.AccountApplication.dao.AccountRepository;
import com.account.AccountApplication.entity.Account;


@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestPropertySource(
		  locations = "classpath:application-test.properties")
@DataJpaTest
public class AccountRepositoryIntegrationTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private AccountRepository accountRepository;
    
    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        Account alex = new Account(15000, 2);
        entityManager.persist(alex);
        entityManager.flush();
     
        // when
        List<Account> found = accountRepository.findByUserId(alex.getUserId());
     
        // then
        assertThat(found.get(0).getAmount())
          .isEqualTo(alex.getAmount());
        
        assertThat(found.get(0).getUserId())
        .isEqualTo(alex.getUserId());
        
        
    }

}
