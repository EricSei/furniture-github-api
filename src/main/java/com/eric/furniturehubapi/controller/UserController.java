package com.eric.furniturehubapi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eric.furniturehubapi.exception.ResourceNotFoundException;
import com.eric.furniturehubapi.model.User;
import com.eric.furniturehubapi.repository.UserRepository;
import com.eric.furniturehubapi.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/health")
	public String getHealth() {
		return "Hello from Furniture API";
	}
	
	@PostMapping("/users")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		System.out.println("User is creating");
		user.setId(null);
		
		user.setPassword(encoder.encode(user.getPassword()));
		User created = repo.save(user);
		return ResponseEntity.status(201).body(created);
		
	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<?> getUserByName(@PathVariable String username) throws ResourceNotFoundException{

	
		Optional<User> found = userService.getUserByName(username);
		
		if(!found.isPresent()) {
			throw new ResourceNotFoundException("user name not found");
		}
		User savedUser = found.get();
		return ResponseEntity.status(201).body(savedUser);
		
	}

}
