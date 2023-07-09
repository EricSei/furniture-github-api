package com.eric.furniturehubapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eric.furniturehubapi.model.User;
import com.eric.furniturehubapi.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> userFound = userRepo.findByName(username);
		
		// if username doesn't exist in the table, throw an exception
		if(userFound.isEmpty()) {
			throw new UsernameNotFoundException(username);
		}
		
		// as long as we found the user, create a user details object with all the relevant info for security 
		// security will take this object and perform authorization & authentication
		return new MyUserDetails( userFound.get() );
	}

}
