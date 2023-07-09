package com.eric.furniturehubapi.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtil {
	
	private final String SECRET_KEY = "ERIC_TEST";//SHOULD NOT USE PROD
	
	
	private String createToken(Map<String, Object> claims, String subject) {
		
		// sets claims
		// subject (user that is being authenticated)
		// set when the token was issued
		// set expiration when token expires and can be no longer used (here its set for 10 hrs)
		// sign it with particular algorithm and secret key that lets you know this token is authentic
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt( new Date( System.currentTimeMillis() ) )
				.setExpiration( new Date( System.currentTimeMillis() + 1000 * 60 * 60 * 10 ) )
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}
	
	public String generateTokens(UserDetails userDetails) {
		
		// claims info/data you want to include in payload of token besides the user info
		Map<String, Object> claims = new HashMap<>();
		
		// returns token for user given along with any claims
		return createToken(claims, userDetails.getUsername());
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		
		final String username = extractUsername(token);
		
		return ( username.equals( userDetails.getUsername() ) && !isTokenExpired(token) );
	}
	
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver ) {
		
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
			
			return extractExpiration(token).before(new Date());
		}
	
	public Date extractExpiration(String token) {
		
		// :: -> method reference
		//    -> pointer, access a method in a class
		//	  -> that way we can pass a method as an argument
		return extractClaim(token, Claims::getExpiration);
	}

	

}
