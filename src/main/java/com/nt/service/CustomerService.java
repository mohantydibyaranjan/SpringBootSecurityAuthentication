package com.nt.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nt.entity.CustomerPWsecurity;
import com.nt.repository.CustomerRepo;
@Service
public class CustomerService implements UserDetailsService {
	@Autowired
	private CustomerRepo repo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		CustomerPWsecurity customer = repo.findByEmail(email);
		return new User(customer.getEmail(), customer.getPwd(), Collections.emptyList());
		
	}
	

}
