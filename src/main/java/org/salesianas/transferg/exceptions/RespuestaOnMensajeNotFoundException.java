package org.salesianas.transferg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RespuestaOnMensajeNotFoundException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public RespuestaOnMensajeNotFoundException(Long id) {
    super("No existen respuestas en el mensaje con id: "+id);
  }
}
