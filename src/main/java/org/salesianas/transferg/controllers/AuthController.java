package org.salesianas.transferg.controllers;

import java.util.Map;

import javax.naming.AuthenticationException;

import org.salesianas.transferg.exceptions.LoginInvalidException;
import org.salesianas.transferg.models.LoginRequest;
import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.services.IAuthService;
import org.salesianas.transferg.services.IUserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class AuthController {

	@Autowired private IAuthService authService;
	@Autowired private AuthenticationManager authManager;
	@Autowired private IUserSecurityService userService;
	
	@Operation(summary = "Registra un nuevo usuario y devuelve token")
	@PostMapping("register")
	public Map<String, Object> registerUser(@RequestBody UserSecurity user)throws Exception{
		return authService.registerUser(user);
	}
	
	@Operation(summary = "Login del usuario y devuelve token")
	@PostMapping("login")
	public Map<String, Object> loginUser(@RequestBody LoginRequest userLogin) throws Exception{
		try {
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword());
			Authentication authentication = authManager.authenticate(authInputToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return authService.loginUser(userLogin);
		}catch(AuthenticationException authExc) {
			throw new LoginInvalidException();
		}
	}
	
	@Operation(summary="Devuelve informacion del usuario")
	@GetMapping("auth")
	public ResponseEntity<UserSecurity> getUserDetails(){
		String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ResponseEntity.ok(userService.getUserByEmail(email));
	}
}
