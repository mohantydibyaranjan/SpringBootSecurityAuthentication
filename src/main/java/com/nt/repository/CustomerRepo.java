package com.nt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.entity.CustomerPWsecurity;

public interface CustomerRepo extends JpaRepository<CustomerPWsecurity, Integer> {
	public CustomerPWsecurity findByEmail(String email);

}
