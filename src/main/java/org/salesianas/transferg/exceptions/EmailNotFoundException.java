package org.salesianas.transferg.exceptions;

public class EmailNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmailNotFoundException(String email) {
		super("El email: " + email + " no existe.");
	}
}
