package org.salesianas.transferg.repositories;

import org.salesianas.transferg.models.Juego;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IJuegoRepository extends JpaRepository<Juego, Long> {

}
