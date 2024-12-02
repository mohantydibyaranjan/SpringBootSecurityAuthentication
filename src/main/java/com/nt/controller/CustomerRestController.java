package com.nt.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.nt.entity.CustomerPWsecurity;
import com.nt.repository.CustomerRepo;
@RestController
public class CustomerRestController { 
	@Autowired
	private CustomerRepo repo;
	@Autowired
	private PasswordEncoder pwd;
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping("/registered")
	public ResponseEntity<String >registration(@RequestBody CustomerPWsecurity c){
		
		String encode = pwd.encode(c.getPwd());
		c.setPwd(encode);
		repo.save(c);
		return new ResponseEntity<String>("User Registered",HttpStatus.CREATED);
	}
	@PostMapping("/login")
	public ResponseEntity<String> login (@RequestBody CustomerPWsecurity c){
		UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(c.getEmail(), c.getPwd());
		try {
		Authentication authenticate = authManager.authenticate(token);
		if(authenticate.isAuthenticated()) {
			return new ResponseEntity<String>("Well come Roshan",HttpStatus.OK);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Invalid credential",HttpStatus.UNAUTHORIZED);
	}
	

}
