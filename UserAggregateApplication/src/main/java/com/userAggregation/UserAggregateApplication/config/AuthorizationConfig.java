package com.userAggregation.UserAggregateApplication.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableAuthorizationServer
//Generate token for oauth2
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {
	
	//Grant_Type User Credential
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
		endpoints.tokenStore(tokenStore())
			.authenticationManager(authenticationManager);
    }
	
	@Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory() 
	        .withClient("client") 
	        .secret("{noop}clientpassword") //to avoid null id
	        .scopes("read", "write") 
	        .authorizedGrantTypes("password")
	        .accessTokenValiditySeconds(3600);
    }
    
	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
	}

}
