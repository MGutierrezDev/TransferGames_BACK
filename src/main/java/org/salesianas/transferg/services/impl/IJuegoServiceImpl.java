package org.salesianas.transferg.services.impl;

import java.util.List;
import org.salesianas.transferg.exceptions.JuegoNotFoundException;
import org.salesianas.transferg.exceptions.UserNotFoundException;
import org.salesianas.transferg.models.Juego;
import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.repositories.IJuegoRepository;
import org.salesianas.transferg.repositories.IUserSecurityRepository;
import org.salesianas.transferg.services.IJuegoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IJuegoServiceImpl implements IJuegoService {

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
    return user.getListaJuegos();
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
  public Juego update(Long id, Juego juego) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void deleteById(Long id) {
    // TODO Auto-generated method stub
    
  }


}
