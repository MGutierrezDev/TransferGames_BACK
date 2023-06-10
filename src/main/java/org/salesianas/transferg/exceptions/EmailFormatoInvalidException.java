package org.salesianas.transferg.exceptions;

public class EmailFormatoInvalidException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailFormatoInvalidException() {
		super("El email introducido es incorrecto");
	}
}
