package org.salesianas.transferg.services.impl;

import java.util.List;

import org.salesianas.transferg.exceptions.PuntuacionNotFoundException;
import org.salesianas.transferg.exceptions.UserNotFoundException;
import org.salesianas.transferg.models.TransferJumpPuntuacion;
import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.repositories.IRepositoryTransferJumpPuntuacion;
import org.salesianas.transferg.repositories.IUserSecurityRepository;
import org.salesianas.transferg.services.ITransferJumpPuntuacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferJumpPuntuacionServiceImpl implements ITransferJumpPuntuacionService {
	
	@Autowired IRepositoryTransferJumpPuntuacion repository;
	@Autowired IUserSecurityRepository userRepository;

	@Override
	public List<TransferJumpPuntuacion> findAll() {
		return repository.findAll();
	}

	@Override
	public TransferJumpPuntuacion findAllByIdUser(Long idUser) {
		UserSecurity user = userRepository.findById(idUser).orElseThrow(()-> new UserNotFoundException(idUser));
		return user.getTransferPuntuacion();
	}

	@Override
	public TransferJumpPuntuacion findById(Long id) {
		return repository.findById(id).orElseThrow(()-> new PuntuacionNotFoundException(id));
	}

	@Override
	public TransferJumpPuntuacion create(TransferJumpPuntuacion puntuacion) {
		return repository.save(puntuacion);
	}

	@Override
	public TransferJumpPuntuacion update(Long id, TransferJumpPuntuacion puntuacion) {
		TransferJumpPuntuacion updatePuntuacion = new TransferJumpPuntuacion();
		updatePuntuacion.setId(puntuacion.getId());
		updatePuntuacion.setPuntuacion(puntuacion.getPuntuacion());
		updatePuntuacion.setUsers(puntuacion.getUsers());
		return repository.save(updatePuntuacion);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
		
	}

}
