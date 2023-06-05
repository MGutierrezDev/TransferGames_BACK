package org.salesianas.transferg.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class JuegoNotFoundException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public JuegoNotFoundException(Long idJuego) {
    super("El juego con id: " + idJuego + " no existe.");
  }
}
