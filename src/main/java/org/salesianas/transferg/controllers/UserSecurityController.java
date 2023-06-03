package org.salesianas.transferg.controllers;

import java.util.List;
import java.util.Objects;

import org.salesianas.transferg.exceptions.EmailInvalidException;
import org.salesianas.transferg.exceptions.UserNotFoundException;
import org.salesianas.transferg.models.ERole;
import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.repositories.ERoleRepository;
import org.salesianas.transferg.repositories.IUserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class UserSecurityController {	
	@Autowired
	private IUserSecurityRepository repository;
	
	private void checkEmailInvalid(UserSecurity user) {
	     UserSecurity userEmail = repository.findByEmail(user.getEmail());
	        if (userEmail != null && !Objects.equals(user.getId(), userEmail.getId())) {
	            throw new EmailInvalidException();
	        }
	}
	
	@PostMapping
	public ResponseEntity<?> save(@RequestBody UserSecurity user){
	  this.checkEmailInvalid(user);
		try {
			String encoderPassword = new BCryptPasswordEncoder().encode(user.getPassword());
			user.setPassword(encoderPassword);
		}catch(DataIntegrityViolationException e){
			
		}
		
		return ResponseEntity.ok(repository.save(user));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserSecurity user){
	  String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
	  UserSecurity updateUser = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	  updateUser.setName(user.getName());
	  updateUser.setEmail(user.getEmail());
	  updateUser.setPassword(encodedPassword);
	  return ResponseEntity.ok(repository.save(updateUser));
	}
}
