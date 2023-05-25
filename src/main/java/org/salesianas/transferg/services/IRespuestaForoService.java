package org.salesianas.transferg.services;

import java.util.List;
import org.salesianas.transferg.exceptions.RespuestaNotFoundException;
import org.salesianas.transferg.models.RespuestaForo;

public interface IRespuestaForoService {
  List<RespuestaForo> findAll();
  List<RespuestaForo> findAllByIdUser(Long idUser);
  List<RespuestaForo> findAllByIdMensaje(Long idMensaje);
  RespuestaForo findById(Long id);
  RespuestaForo create(RespuestaForo respuesta);
  RespuestaForo createByIdUserAndIdMensaje(Long idUser, Long idMensaje, RespuestaForo respuesta);
  RespuestaForo update(Long id, RespuestaForo respuesta);
  void deleteById(Long id);
}
