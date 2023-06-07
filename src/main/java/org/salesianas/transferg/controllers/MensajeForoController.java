package org.salesianas.transferg.controllers;

import org.salesianas.transferg.exceptions.MensajeNotFoundException;
import org.salesianas.transferg.exceptions.MensajeOnUserNotFoundException;
import org.salesianas.transferg.models.MensajeForo;
import org.salesianas.transferg.services.IMensajeForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MensajeForoController {

  @Autowired
  private IMensajeForoService service;

  @GetMapping("/users/mensajes")
  public ResponseEntity<?> listAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/users/{idUser}/mensajes")
	public ResponseEntity<?> listAllByIdUser(@PathVariable Long idUser) throws MensajeOnUserNotFoundException{
	    return ResponseEntity.ok(service.findAllByIdUser(idUser));
	}

  @GetMapping("/users/mensajes/{id}")
  public ResponseEntity<?> findById(@PathVariable Long id) throws MensajeNotFoundException {
    return ResponseEntity.ok(service.findById(id));
  }

  @PostMapping("/users/mensajes")
  public ResponseEntity<?> create(@RequestBody MensajeForo mensaje) {
    return ResponseEntity.ok(service.create(mensaje));
  }

  @PostMapping("/users/{idUser}/mensajes")
  public ResponseEntity<?> createWithUser(@RequestBody MensajeForo mensaje,
      @PathVariable Long idUser) {
    return ResponseEntity.ok(service.createWithUser(mensaje, idUser));
  }

  @PutMapping("/users/mensajes/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody MensajeForo mensaje) {
    return ResponseEntity.ok(service.update(id, mensaje));
  }

  @DeleteMapping("admin/users/mensajes/{id}")
  public String deleteById(@PathVariable Long id) {
    service.deleteById(id);
    return "Mensaje eliminado correctamente";
  }
}
