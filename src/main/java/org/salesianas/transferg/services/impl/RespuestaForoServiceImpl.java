package org.salesianas.transferg.services.impl;

import java.util.List;
import java.util.Optional;
import org.salesianas.transferg.exceptions.MensajeNotFoundException;
import org.salesianas.transferg.exceptions.RespuestaNotFoundException;
import org.salesianas.transferg.exceptions.RespuestaOnMensajeNotFoundException;
import org.salesianas.transferg.exceptions.RespuestaOnUserNotFoundException;
import org.salesianas.transferg.exceptions.UserNotFoundException;
import org.salesianas.transferg.models.MensajeForo;
import org.salesianas.transferg.models.RespuestaForo;
import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.repositories.IMensajeForoRepository;
import org.salesianas.transferg.repositories.IRespuestaForoRepository;
import org.salesianas.transferg.repositories.IUserSecurityRepository;
import org.salesianas.transferg.services.IRespuestaForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaForoServiceImpl implements IRespuestaForoService {

  @Autowired
  private IUserSecurityRepository userRepository;
  
  @Autowired
  private IRespuestaForoRepository respuestaRepository;
  
  @Autowired
  private IMensajeForoRepository mensajeRepository;
  
  @Override
  public List<RespuestaForo> findAll() {
    return respuestaRepository.findAll();
  }
  
  @Override
  public List<RespuestaForo> findAllByIdUser(Long idUser) {
    UserSecurity user = userRepository.findById(idUser).orElseThrow(() -> new RespuestaOnUserNotFoundException(idUser));
    return user.getRespuestas();
  }

  @Override
  public List<RespuestaForo> findAllByIdMensaje(Long idMensaje) {
    MensajeForo mensaje = mensajeRepository.findById(idMensaje).orElseThrow(() -> new RespuestaOnMensajeNotFoundException(idMensaje));
    return mensaje.getRespuestas();
  }

  @Override
  public RespuestaForo findById(Long id){
    return respuestaRepository.findById(id).orElseThrow(() -> new RespuestaNotFoundException(id));
  }

  @Override
  public RespuestaForo create(RespuestaForo respuesta) {
    return respuestaRepository.save(respuesta);
  }

  @Override
  public RespuestaForo createByIdUserAndIdMensaje(Long idUser, Long idMensaje,
      RespuestaForo respuesta) {
    UserSecurity user = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));
    MensajeForo mensaje = mensajeRepository.findById(idMensaje).orElseThrow(() -> new MensajeNotFoundException(idMensaje));
    RespuestaForo updateRespuesta = new RespuestaForo();
    updateRespuesta.setTitulo("Resp: " + mensaje.getAsunto());
    updateRespuesta.setRespuesta(respuesta.getRespuesta());
    updateRespuesta.setDate(respuesta.getDate());
    updateRespuesta.setUser(user);
    updateRespuesta.setMensajeForo(mensaje);
    return respuestaRepository.save(updateRespuesta);
  }

  @Override
  public RespuestaForo update(Long id, RespuestaForo respuesta) {
    RespuestaForo updateRespuesta = respuestaRepository.findById(id).orElseThrow(() -> new RespuestaNotFoundException(id));
    MensajeForo mensaje = updateRespuesta.getMensajeForo();
    updateRespuesta.setTitulo(mensaje.getAsunto());
    updateRespuesta.setRespuesta(respuesta.getRespuesta());
    updateRespuesta.setDate(respuesta.getDate());
    return respuestaRepository.save(updateRespuesta);
  }

  @Override
  public void deleteById(Long id) {
    RespuestaForo respuesta = respuestaRepository.findById(id).orElseThrow(() -> new RespuestaNotFoundException(id));
    respuestaRepository.delete(respuesta);
  }

}
