package com.eric.furniturehubapi.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.eric.furniturehubapi.model.User;
import com.eric.furniturehubapi.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AuthenticationManager authenticationManager;
		
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	public Optional<User> getUserByid(int id){
		
		Optional<User> found = userRepo.findById(id);
		return found;
	}
	
	public Optional<User> getUserByName(String name){
		Optional<User> found = userRepo.findByName(name);
		return found;
	}
	
	public Optional<User> getUserByEmail(String email){
		Optional<User> found = userRepo.findByEmail(email);
		return found;
	}
	
	public Optional<User> getAuthenticatedUserDetail(HttpServletRequest req) throws Exception{
		System.out.println( req.getHeader("Authorization").split(" ")[1]);
		
		String jwt = req.getHeader("Authorization").split(" ")[1];
		String username = jwtUtil.extractUsername(jwt);
		System.out.println("lgos..........." + username);
		Optional<User> found = userRepo.findByName(username);
		
		return found;
		
	}
	
	
	public User createUser(User user) {
		
		user.setId(null);
		// use a password encoder to encode the password so it is not saved as plain text in the database
		user.setPassword(encoder.encode(user.getPassword()));
		
		User created = userRepo.save(user);
		
		
		return created;
	}
	
	
	public User updateUser(User user) {
		
		if(userRepo.existsById(user.getId())) {
			user.setPassword(encoder.encode(user.getPassword()));
			User updated = userRepo.save(user);
			return updated;
			
		}
		
		return null;
		
	}
	
	
	public boolean deleteUserById(int id){
		if(userRepo.existsById(id)) {
			userRepo.deleteById(id);
			return true;
		}
		return false;
	}
	
	
	
}
