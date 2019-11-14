package com.user.UserApplication.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.user.UserApplication.dao.UserRepository;
import com.user.UserApplication.entity.User;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestPropertySource(
		  locations = "classpath:application-test.properties")
@DataJpaTest
public class UserRepositoryIntegrationTest {
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void whenFindByName_thenReturnEmployee() {
        // given
        User alex = new User("alex", 10000, "alex@virtusa.com");
        entityManager.persist(alex);
        entityManager.flush();
     
        // when
        User found = userRepository.findByName(alex.getName());
     
        // then
        assertThat(found.getName())
          .isEqualTo(alex.getName());
        
        assertThat(found.getSalary())
        .isEqualTo(alex.getSalary());
        
        assertThat(found.getEmail())
        .isEqualTo(alex.getEmail());
    }

}
