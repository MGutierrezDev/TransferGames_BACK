package org.salesianas.transferg.exceptions;

public class LoginInvalidException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginInvalidException() {
		super("Email or password invalid");
	}
}
