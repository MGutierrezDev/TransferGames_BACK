package org.salesianas.transferg.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.salesianas.transferg.exceptions.EmailInvalidException;
import org.salesianas.transferg.exceptions.PasswordInvalidException;
import org.salesianas.transferg.exceptions.UserNotFoundException;
import org.salesianas.transferg.models.ERole;
import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.repositories.IRoleRepository;
import org.salesianas.transferg.repositories.IUserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSecurityController {	
	@Autowired
	private IUserSecurityRepository repository;
	
	@Autowired IRoleRepository roleRepository;
	
	private void checkEmailInvalid(UserSecurity user) {
	     UserSecurity userEmail = repository.findByEmail(user.getEmail());
	        if (userEmail != null && !Objects.equals(user.getId(), userEmail.getId())) {
	            throw new EmailInvalidException();
	        }
	}
	private void checkPasswordInvalid(UserSecurity user) {
		if(user.getPassword().length()<6) {
			throw new PasswordInvalidException();
		}
	}
	
	@GetMapping("user/{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable String email){
		return ResponseEntity.ok(repository.findOneByEmail(email));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> save(@RequestBody UserSecurity user){
	    try {
	        checkEmailInvalid(user);
	        checkPasswordInvalid(user);
			String encoderPassword = new BCryptPasswordEncoder().encode(user.getPassword());
			user.setPassword(encoderPassword);
			ERole rolAdmin = roleRepository.findByName("ADMIN");
			ERole rolUser = roleRepository.findByName("USER");
			UserSecurity saveUser = new UserSecurity();
			
			if("ADMIN".equals(user.getRole().getName())) {
				saveUser.setRole(rolAdmin);
			}
			else if("USER".equals(user.getRole().getName())) {
				saveUser.setRole(rolUser);
			}
			else {
				saveUser.setRole(rolUser);
			}
			
			saveUser.setName(user.getName());
			saveUser.setEmail(user.getEmail());
			saveUser.setPassword(encoderPassword);
			
			return ResponseEntity.ok(repository.save(saveUser));
	    } catch (EmailInvalidException e) {
	        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
	    } catch (PasswordInvalidException e) {
	        return ResponseEntity.badRequest().body("Error: " + e.getMessage());
	    }
	}
	
	@PutMapping("/register/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserSecurity user){
	  String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
	  UserSecurity updateUser = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	  updateUser.setName(user.getName());
	  updateUser.setEmail(user.getEmail());
	  updateUser.setPassword(encodedPassword);
	  return ResponseEntity.ok(repository.save(updateUser));
	}
	
	@DeleteMapping("/admin/user/{id}")
	public String delete(@PathVariable Long id){
		UserSecurity user = repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		if (!user.getMensajes().isEmpty()) {
		    user.getMensajes().clear();
		}

		if (!user.getRespuestas().isEmpty()) {
		    user.getRespuestas().clear();
		}
		
		repository.delete(user);
		return "Usuario eliminado con exito";
	}
}
