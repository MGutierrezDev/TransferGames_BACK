package org.salesianas.transferg.security.filter;

import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.salesianas.transferg.security.AuthCredentials;
import org.salesianas.transferg.security.TokenUtils;
import org.salesianas.transferg.security.impl.UserDetailImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
		AuthCredentials authCredentials = new AuthCredentials();
		try {
			authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
		}catch(IOException | java.io.IOException e) {
			
		}
		UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
				authCredentials.getEmail(),
				authCredentials.getPassword(),
				Collections.emptyList()
				);
		
		return getAuthenticationManager().authenticate(usernamePAT);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, 
											HttpServletResponse response, 
											FilterChain chain, 
											Authentication authResult) throws ServletException, java.io.IOException{
		UserDetailImpl userDetails = (UserDetailImpl) authResult.getPrincipal();
		String token = TokenUtils.createToken(userDetails.getName(), userDetails.getUsername(), userDetails.getAuthorities());
		response.addHeader("Authorization", "" + token);
		response.getWriter().flush();
		super.successfulAuthentication(request, response, chain, authResult);
	}
}
