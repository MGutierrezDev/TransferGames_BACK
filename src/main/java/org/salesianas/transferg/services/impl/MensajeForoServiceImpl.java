package org.salesianas.transferg.services.impl;

import java.util.List;

import org.salesianas.transferg.models.MensajeForo;
import org.salesianas.transferg.repositories.IMensajeForoRepository;
import org.salesianas.transferg.repositories.IUserSecurityRepository;
import org.salesianas.transferg.services.IMensajeForoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MensajeForoServiceImpl implements IMensajeForoService {
	
	@Autowired
	private IMensajeForoRepository repository;
	
	@Autowired
	private IUserSecurityRepository userRepository;

	@Override
	public List<MensajeForo> findAll() {
		return repository.findAll();
	}

	@Override
	public MensajeForo findById(Long id) {
		return repository.findById(id).get();
	}

	@Override
	public MensajeForo create(MensajeForo mensajeForo) {
		return repository.save(mensajeForo);
	}
	
	@Override
	public MensajeForo createWithUser(MensajeForo mensaje, Long idUser) {
		mensaje.setUser(userRepository.findById(idUser).get());
		return repository.save(mensaje);
	}

	@Override
	public MensajeForo update(Long id, MensajeForo mensajeForo) {
		MensajeForo updateMensaje = repository.findById(id).get();
		updateMensaje.setAsunto(mensajeForo.getAsunto());
		updateMensaje.setTexto(mensajeForo.getTexto());
		updateMensaje.setDate(mensajeForo.getDate());
		return repository.save(updateMensaje);
	}
	
	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);	
	}
}
