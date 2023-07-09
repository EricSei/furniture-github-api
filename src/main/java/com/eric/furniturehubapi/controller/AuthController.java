package com.eric.furniturehubapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eric.furniturehubapi.model.AuthenticationRequest;
import com.eric.furniturehubapi.model.AuthenticationResponse;
import com.eric.furniturehubapi.service.JwtUtil;

@RestController
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	// create the token at http://localhost:8080/authenticate 
		// send the username & password and try to generate a token as a response
		@PostMapping("api/authenticate")
		public ResponseEntity<?> createJwtToken(@RequestBody AuthenticationRequest request) throws Exception {
			
			// try to catch the exception for bad credentials, just so we can set our own
			// message when this doesn't work
			System.out.println("XXXXXXXXXXXXXXXXXXX "+request.getUsername());
			System.out.println("XXXXXXXXXXXXXXXXXXX "+request.getPassword());
			try {
				// make sure we have a valid user by checking their username and password
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

			} catch (BadCredentialsException e) {
				// provide our own message on why login didn't work
				throw new Exception("Incorrect username or password");
			}

			// as long as no exception was thrown, user is valid

			// load in the user details for that user
			final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
			System.out.print(userDetails.isEnabled());
			// generate the token for that user
			final String jwt = jwtUtil.generateTokens(userDetails);
			System.out.println(" -- - - - - -- - - -"+jwt);
			// return the token
			return ResponseEntity.status(201).body( new AuthenticationResponse(jwt) );

		}

}
