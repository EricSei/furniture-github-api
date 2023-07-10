package com.eric.furniturehubapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

import com.eric.furniturehubapi.util.JwtRequestFilter;



@Configuration
public class SecurityConfiguration {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	@Bean
	protected UserDetailsService userDetailsService() {
		return userDetailsService;
	}
	
	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf()
		.disable()
		.authorizeRequests()
		
		.antMatchers(HttpMethod.POST, "/api/users").permitAll()
		.antMatchers("/api/health").permitAll()
		.antMatchers("/api/authenticate").permitAll()
		.antMatchers(HttpMethod.POST, "/api/products").hasRole("ADMIN")
		//swagger
		.antMatchers( HttpMethod.GET, 	"/v3/api-docs/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS );

	// this request will go through many filters, make sure that the FIRST filter that is checked is
	// the filter for jwts, in order to make sure of that, the filter has to be checked before you check the 
	// username & password (filter)
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		http.cors();
	
		return http.build();
	}
	
	@Bean
	protected PasswordEncoder encoder() {
		
		// plain text encoder -> won't do any encoding
		//return NoOpPasswordEncoder.getInstance();
		
		// there's many options for password encoding, just pick a algorithm that you like
		return new BCryptPasswordEncoder(); 
	}
	
	@Bean
	protected DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder( encoder() );
		
		return authProvider;
	}
	
	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}

}
