package org.salesianas.transferg.services;

import java.io.IOException;
import java.util.List;

import org.salesianas.transferg.models.Juego;
import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.models.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IJuegoService {
  List<Juego> findAll();
  List<Juego> findAllByIdUser(Long idUser);
  Juego findById(Long id);
  Juego create(Juego juego);
  Juego update(Long id, Juego newJuego);
  void deleteById(Long id);
  public Juego saveImageJuego(MultipartFile img, Juego juego) throws IOException;
}
