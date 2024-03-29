package org.salesianas.transferg.services;

import java.util.List;

import org.salesianas.transferg.models.MensajeForo;
import org.springframework.stereotype.Service;

@Service
public interface IMensajeForoService {
	List<MensajeForo> findAll();
	List<MensajeForo> findAllByIdUser(Long idUser);
	MensajeForo findById(Long id);
	MensajeForo create(MensajeForo mensajeForo);
	MensajeForo createWithUser(MensajeForo mensaje, Long idUser);
	MensajeForo update(Long id, MensajeForo mensajeForo);
	void deleteById(Long id);
}
