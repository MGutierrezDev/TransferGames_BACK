package org.salesianas.transferg.controllers;

import org.salesianas.transferg.exceptions.RespuestaNotFoundException;
import org.salesianas.transferg.models.RespuestaForo;
import org.salesianas.transferg.services.IRespuestaForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RespuestaForoController {

  @Autowired
  private IRespuestaForoService service;

  @GetMapping("/users/mensajes/respuestas")
  public ResponseEntity<?> listAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @GetMapping("/users/{idUser}/mensajes/respuestas")
  public ResponseEntity<?> listAllByIdUser(@PathVariable Long idUser) {
    return ResponseEntity.ok(service.findAllByIdUser(idUser));
  }

  @GetMapping("/users/mensajes/{idMensaje}/respuestas")
  public ResponseEntity<?> listAllByIdMensaje(@PathVariable Long idMensaje) {
    return ResponseEntity.ok(service.findAllByIdMensaje(idMensaje));
  }

  @GetMapping("/users/mensajes/respuestas/{idRespuesta}")
  public ResponseEntity<?> findById(@PathVariable Long idRespuesta) throws RespuestaNotFoundException {
    return ResponseEntity.ok(service.findById(idRespuesta));
  }

  @PostMapping("/users/mensajes/respuestas")
  public ResponseEntity<?> create(@RequestBody RespuestaForo respuesta) {
    return ResponseEntity.ok(service.create(respuesta));
  }

  @PostMapping("/users{idUser}/mensajes/{idMensaje}/respuestas")
  public ResponseEntity<?> createByIdUserAndIdMensaje(@PathVariable Long idUser,
      @PathVariable Long idMensaje, @RequestBody RespuestaForo respuesta) {
    return ResponseEntity.ok(service.createByIdUserAndIdMensaje(idUser, idMensaje, respuesta));
  }

  @PutMapping("/users/mensajes/respuestas/{idRespuesta}")
  public ResponseEntity<?> update(@PathVariable Long idRespuesta,
      @RequestBody RespuestaForo respuesta) {
    return ResponseEntity.ok(service.update(idRespuesta, respuesta));
  }

  @DeleteMapping("admin/usersmensajes/respuestas/{idRespuesta}")
  public String delete(@PathVariable Long idRespuesta) {
    service.deleteById(idRespuesta);
    return "Respuesta eliminada correctamente";
  }
}
