package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.User;
import com.app.exception.SMSException;
import com.app.model.AuthRequest;
import com.app.security.JwtService;
import com.app.service.IUserService;
import com.app.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
	@Autowired
	private IUserService service;

	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;


	@PostMapping("/create")
	ResponseEntity<String> create(@RequestBody User user) {
		
		service.createUser(user);
		String msg = "User Created Successfully!!";
		return ResponseEntity.ok(msg);

	}

	@PostMapping("/authenticate")
	public String authenticationAndGetToken(@RequestBody AuthRequest authRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUsername());

		} else {
			
			throw new SMSException("invalid user request!");
		}
	}
}
