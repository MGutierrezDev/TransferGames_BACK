package org.salesianas.transferg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MensajeOnUserNotFoundException extends RuntimeException {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public MensajeOnUserNotFoundException(Long id) {
    super("No existen mensajes en el user con id: "+id);
  }

}
