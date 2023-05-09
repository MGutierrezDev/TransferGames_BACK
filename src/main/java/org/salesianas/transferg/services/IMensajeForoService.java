package org.salesianas.transferg.services;

import java.util.List;

import org.salesianas.transferg.models.MensajeForo;

public interface IMensajeForoService {
	List<MensajeForo> findAll();
	MensajeForo findById(Long id);
	MensajeForo create(MensajeForo mensajeForo);
	MensajeForo createWithUser(MensajeForo mensaje, Long idUser);
	MensajeForo update(Long id, MensajeForo mensajeForo);
	void deleteById(Long id);
}
