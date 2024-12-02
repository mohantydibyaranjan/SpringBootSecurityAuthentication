package com.nt.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nt.service.CustomerService;

@Configuration
public class AppConfiguration {
	@Autowired
	private CustomerService service;
	@Bean
	public PasswordEncoder encode() {
		// to encrypt original pw
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationProvider authProvider() {
		// to load user record using service 
		DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
		dao.setUserDetailsService(service);
		dao.setPasswordEncoder(encode());
		return dao;
		
	}
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration config) throws Exception {
		// to manage authentication ,validating credential,verifying the login functionality...
		return config.getAuthenticationManager();
	}
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//to customize security configuration
		return http.csrf().disable().authorizeHttpRequests().requestMatchers("/registered","/login").permitAll().and().build();
	}
	
	

}
