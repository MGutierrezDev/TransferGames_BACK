package org.salesianas.transferg.controllers;

import org.salesianas.transferg.models.Juego;
import org.salesianas.transferg.services.IJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JuegoController {

  @Autowired
  private IJuegoService service;
  
  @GetMapping("juego")
  public ResponseEntity<?> getAll(){
    return ResponseEntity.ok(service.findAll());
  }
  
  @GetMapping("user/{idUser}/juego")
  public ResponseEntity<?> getByIdUser(@PathVariable Long idUser){
    return ResponseEntity.ok(service.findAllByIdUser(idUser));
  }
  
  @GetMapping("juego/{idJuego}")
  public ResponseEntity<?> getById(@PathVariable Long idJuego){
    return ResponseEntity.ok(service.findById(idJuego));
  }
  
  @PostMapping("admin/juego")
  public ResponseEntity<?> create(@RequestBody Juego juego){
    return ResponseEntity.ok(service.create(juego));
  }
}
