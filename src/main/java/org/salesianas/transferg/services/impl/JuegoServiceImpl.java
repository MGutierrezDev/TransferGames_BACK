package org.salesianas.transferg.services.impl;

import java.io.IOException;
import java.util.List;

import org.salesianas.transferg.exceptions.JuegoNotFoundException;
import org.salesianas.transferg.exceptions.UserNotFoundException;
import org.salesianas.transferg.models.Juego;
import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.repositories.IJuegoRepository;
import org.salesianas.transferg.repositories.IUserSecurityRepository;
import org.salesianas.transferg.services.IJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class JuegoServiceImpl implements IJuegoService {

  @Autowired
  private IJuegoRepository repository;
  
  @Autowired
  private IUserSecurityRepository userRepository;
  @Override
  public List<Juego> findAll() {
    return repository.findAll();
  }

  @Override
  public List<Juego> findAllByIdUser(Long idUser) {
    UserSecurity user = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException(idUser));
    return user.getJuegos();
  }

  @Override
  public Juego findById(Long id) {
    return repository.findById(id).orElseThrow(()-> new JuegoNotFoundException(id));
  }

  @Override
  public Juego create(Juego juego) {
    return repository.save(juego);
  }


  @Override
  public Juego update(Long id, Juego newJuego) {
    Juego juego = repository.findById(id).orElseThrow(()-> new JuegoNotFoundException(id));
    juego.setNombre(newJuego.getNombre());
    juego.setDescripcion(newJuego.getDescripcion());
    juego.setDireccion(newJuego.getDireccion());
    juego.setImage(newJuego.getImage());
    juego.setUsuarios(newJuego.getUsuarios());
    return repository.save(juego);
  }

  @Override
  public void deleteById(Long id) {
    Juego juego = repository.findById(id).orElseThrow(()->new JuegoNotFoundException(id));
    if(!juego.getUsuarios().isEmpty()) {
    	juego.getUsuarios().clear();
    }
    repository.delete(juego);
  }

@Override
public Juego saveImageJuego(MultipartFile img, Juego juego) throws IOException {
	byte[] bytes = null;
	if (!img.isEmpty()) {
		bytes = img.getBytes();
	}
	juego.setImage(bytes);
	return repository.save(juego);
}


}
