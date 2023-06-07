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
import org.salesianas.transferg.services.IUserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class UserSecurityController {	
	@Autowired
	private IUserSecurityService userService;
	
	@Autowired IRoleRepository roleRepository;
	
	private void checkEmailInvalid(UserSecurity user) throws Exception {
	     UserSecurity userEmail = userService.getUserByEmail(user.getEmail());
	        if (userEmail != null && !Objects.equals(user.getId(), userEmail.getId())) {
	            throw new EmailInvalidException();
	        }
	}
	private void checkPasswordInvalid(UserSecurity user) {
		if(user.getPassword().length()<6) {
			throw new PasswordInvalidException();
		}
	}
	
	@Operation(summary = "Devuelve un user por id")
	@GetMapping("user/{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable String email) throws Exception{
		return ResponseEntity.ok(userService.getUserByEmail(email));
	}

	@Operation(summary = "")
	@PutMapping("/user/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserSecurity user) throws Exception{
	  String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
	  UserSecurity updateUser = userService.getUserById(id);
	  updateUser.setName(user.getName());
	  updateUser.setEmail(user.getEmail());
	  updateUser.setPassword(encodedPassword);
	  return ResponseEntity.ok(userService.saveUser(updateUser));
	}
	
	@DeleteMapping("/admin/user/{id}")
	public String delete(@PathVariable Long id) throws Exception{
		UserSecurity user = userService.getUserById(id);
		if (!user.getMensajes().isEmpty()) {
		    user.getMensajes().clear();
		}

		if (!user.getRespuestas().isEmpty()) {
		    user.getRespuestas().clear();
		}
		
		userService.deleteUser(user);
		return "Usuario eliminado con exito";
	}
}
