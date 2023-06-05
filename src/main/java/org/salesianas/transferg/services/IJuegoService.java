package org.salesianas.transferg.services;

import java.util.List;
import org.salesianas.transferg.models.Juego;

public interface IJuegoService {
  List<Juego> findAll();
  List<Juego> findAllByIdUser(Long idUser);
  Juego findById(Long id);
  Juego create(Juego juego);
  Juego update(Long id, Juego juego);
  void deleteById(Long id);
}
