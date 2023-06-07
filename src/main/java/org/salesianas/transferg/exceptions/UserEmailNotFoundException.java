package org.salesianas.transferg.exceptions;

public class UserEmailNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserEmailNotFoundException(String email) {
		super("El user con el email: " + email + " no existe.");
	}
}
