package org.salesianas.transferg.exceptions;

public class BlankNameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BlankNameException() {
		super("Nombre incorrecto. Debe tener mínimo 2 caracteres");
	}
}
