package org.salesianas.transferg.services;

import java.util.List;

import org.salesianas.transferg.models.TransferJumpPuntuacion;
import org.springframework.stereotype.Service;

@Service
public interface ITransferJumpPuntuacionService {
	List<TransferJumpPuntuacion> findAll();
	TransferJumpPuntuacion findAllByIdUser(Long idUser);
	TransferJumpPuntuacion findById(Long id);
	TransferJumpPuntuacion create(TransferJumpPuntuacion puntuacion);
	TransferJumpPuntuacion update(Long id, TransferJumpPuntuacion puntuacion);
	void deleteById(Long id);
}
