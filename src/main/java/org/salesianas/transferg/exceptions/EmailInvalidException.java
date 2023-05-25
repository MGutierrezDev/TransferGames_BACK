package org.salesianas.transferg.exceptions;


public class EmailInvalidException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public EmailInvalidException() {
    super("El email introducido ya existe en la bbdd");
  }
}
