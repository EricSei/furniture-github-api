package com.eric.furniturehubapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.eric.furniturehubapi.model.User;
import com.eric.furniturehubapi.repository.UserRepository;
import com.eric.furniturehubapi.service.UserService;

@SpringBootTest
class FurnitureHubApiApplicationTests {

	@Autowired
	private UserService service;
	
	@MockBean
	private UserRepository repository;
	
	@Test
	public void getUserTest( ) {
		
		Optional<User> opt = Optional.of(new User(1, "john@gmail.com" , "john", "john@123", "ROLE_USER", true));
		
		when(repository.findByEmail("john@gmail.com"))
		.thenReturn( opt);
		
		assertEquals( opt.get(), service.getUserByEmail("john@gmail.com").get());
	}
	
	@Test
	public void getAllUsersTest( ) {
		
		when(repository.findAll())
		.thenReturn( 
				Stream.of(
						new User(1, "john@gmail.com" , "john", "john@123", "ROLE_USER", true), 
						new User( 2, "jay@gmail.com" , "jay", "jay@123", "ROLE_USER", true) )
				.collect(Collectors.toList()) );
		
		assertEquals( 2, service.getAllUsers().size());
	}

}
