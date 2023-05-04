package org.salesianas.transferg.controllers;

import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.repositories.IUserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UserSecurityController {
	
	@Autowired
	private IUserSecurityRepository repository;
	
	@GetMapping
	public ResponseEntity<?> listAll() {
        return ResponseEntity.ok(repository.findAll());
    }
	@PostMapping
	public ResponseEntity<?> save(@RequestBody UserSecurity user){
		return ResponseEntity.ok(repository.save(user));
	}
}
