package org.salesianas.transferg.exceptions;

public class PasswordInvalidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  public PasswordInvalidException() {
		    super("La contraseña no es válida. Debe tener como minimo 6 caracteres");
		  }
}
