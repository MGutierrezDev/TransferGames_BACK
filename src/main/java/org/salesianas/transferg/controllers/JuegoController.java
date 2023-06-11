package org.salesianas.transferg.controllers;

import java.io.IOException;

import org.salesianas.transferg.models.Juego;
import org.salesianas.transferg.services.IJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.Operation;

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
  
  @Operation(summary="Recibe un juego y la id del juego a actualizar y lo actualiza")
  @PutMapping("admin/juego/{id}")
  public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Juego juego){
	  return ResponseEntity.ok(service.update(id, juego));
  }
  @Operation(summary = "Recibe una imagen y la asocia al juego que se le indica en el id")
  @PutMapping("admin/juego/{id}/image")
  public void uploadImage(@PathVariable Long id, @RequestParam("juegoImg") MultipartFile img) throws Exception {
	  Juego juego = service.findById(id);
	  try {
		  service.saveImageJuego(img, juego);
	  } catch (IOException e) {
		  e.printStackTrace();
	  }
  }
  @Operation(summary="Recibe el id de un juego y lo elimina")
  @DeleteMapping("admin/juego/{id}")
  public void delete(@PathVariable Long id){
	  service.deleteById(id);
  }
  
}
