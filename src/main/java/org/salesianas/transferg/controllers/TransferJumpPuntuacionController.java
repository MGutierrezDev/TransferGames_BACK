package org.salesianas.transferg.controllers;

import org.salesianas.transferg.models.TransferJumpPuntuacion;
import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.services.ITransferJumpPuntuacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/juegos/puntuacionTransfer")
public class TransferJumpPuntuacionController {
	
	@Autowired ITransferJumpPuntuacionService service;
	
	@Operation(summary = "Devuelve una puntuacion por id")
	@GetMapping("/{id}")
	public ResponseEntity<?> getPuntuacionByEId(@PathVariable Long id) throws Exception{
		return ResponseEntity.ok(service.findById(id));
	}
	
	@Operation(summary = "Devuelve todas las puntuaciones")
	@GetMapping()
	public ResponseEntity<?> getPuntuacion() throws Exception{
		return ResponseEntity.ok(service.findAll());
	}

	@Operation(summary = "Actualiza una puntuacion mediante su id")
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @RequestBody TransferJumpPuntuacion newPuntuacion) throws Exception{
	  return ResponseEntity.ok(service.update(id, newPuntuacion));
	}
	
	@Operation(summary = "Elimina un user mediante su id")
	@DeleteMapping("/admin/user/{id}")
	public String delete(@PathVariable Long id) throws Exception{
		service.deleteById(id);
		return "Usuario eliminado con exito";
	}
}
