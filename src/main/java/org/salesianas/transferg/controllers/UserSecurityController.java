package org.salesianas.transferg.controllers;

import java.io.IOException;

import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.repositories.IRoleRepository;
import org.salesianas.transferg.services.IUserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class UserSecurityController {	
	@Autowired
	private IUserSecurityService userService;
	
	@Autowired IRoleRepository roleRepository;
	
	@Operation(summary = "Devuelve un user por email")
	@GetMapping("user/{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable String email) throws Exception{
		return ResponseEntity.ok(userService.getUserByEmail(email));
	}
	
	@Operation(summary = "Devuelve un user por id")
	@GetMapping("admin/user/{id}")
	public ResponseEntity<?> getUserByEId(@PathVariable Long id) throws Exception{
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@Operation(summary = "Devuelve todos los usuarios")
	@GetMapping("admin/user")
	public ResponseEntity<?> getUser() throws Exception{
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@Operation(summary = "Actualiza un user mediante su id")
	@PutMapping("/user/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UserSecurity newUser) throws Exception{
	  return ResponseEntity.ok(userService.updateUser(id, newUser));
	}
	
	@Operation(summary = "Elimina un user mediante su id")
	@DeleteMapping("/admin/user/{id}")
	public void delete(@PathVariable Long id) throws Exception{
		UserSecurity user = userService.getUserById(id);
		if (!user.getMensajes().isEmpty()) {
		    user.getMensajes().clear();
		}

		if (!user.getRespuestas().isEmpty()) {
		    user.getRespuestas().clear();
		}
		
		if (!user.getJuegos().isEmpty()) {
			user.getJuegos().clear();
		}
		
		userService.deleteUser(user);
	}
	@Operation(summary = "Recibe una imagen y la asocia al usuario que se le indica en el id")
	@PutMapping("user/{id}/image")
	public void uploadImage(@PathVariable Long id, @RequestParam("userImg") MultipartFile img) throws Exception {
		UserSecurity user = userService.getUserById(id);
		try {
			userService.saveImageUser(img, user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
