package org.salesianas.transferg.exceptions;

public class PuntuacionNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PuntuacionNotFoundException(Long id) {
		super("La puntuacion con id: " + id + " no existe.");
	}
}
