package org.salesianas.transferg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RespuestaNotFoundException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public RespuestaNotFoundException(Long id) {
    super("Respuesta con id: "+id+" no existe.");
  }
}
