package org.salesianas.transferg.controllers;

import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.repositories.IUserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class UserSecurityController {	
	@Autowired
	private IUserSecurityRepository repository;
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody UserSecurity user){
		try {
			String encoderPassword = new BCryptPasswordEncoder().encode(user.getPassword());
			user.setPassword(encoderPassword);
		}catch(DataIntegrityViolationException e){
			
		}
		
		return ResponseEntity.ok(repository.save(user));
	}
}
