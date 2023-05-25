package org.salesianas.transferg.services.impl;

import java.util.List;
import java.util.Optional;
import org.salesianas.transferg.exceptions.MensajeNotFoundException;
import org.salesianas.transferg.exceptions.MensajeOnUserNotFoundException;
import org.salesianas.transferg.exceptions.UserNotFoundException;
import org.salesianas.transferg.models.MensajeForo;
import org.salesianas.transferg.models.UserSecurity;
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
	  public List<MensajeForo> findAllByIdUser(Long idUser) {
	    UserSecurity user = userRepository.findById(idUser).orElseThrow(() -> new MensajeOnUserNotFoundException(idUser));
	    return user.getMensajes();
	}

	@Override
	public MensajeForo findById(Long id){
	  return repository.findById(id).orElseThrow(() -> new MensajeNotFoundException(id));
	}

	@Override
	public MensajeForo create(MensajeForo mensajeForo) {
		return repository.save(mensajeForo);
	}
	
	@Override
	public MensajeForo createWithUser(MensajeForo mensaje, Long idUser) {
		mensaje.setUser(userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser)));
		return repository.save(mensaje);
	}

	@Override
	public MensajeForo update(Long id, MensajeForo mensajeForo) {
		MensajeForo updateMensaje = repository.findById(id).orElseThrow(() -> new MensajeNotFoundException(id));
		updateMensaje.setAsunto(mensajeForo.getAsunto());
		updateMensaje.setTexto(mensajeForo.getTexto());
		updateMensaje.setDate(mensajeForo.getDate());
		return repository.save(updateMensaje);
	}
	
	@Override
	public void deleteById(Long id) {
	  MensajeForo mensaje = repository.findById(id).orElseThrow(() -> new MensajeNotFoundException(id));
		repository.delete(mensaje);	
	}
}
