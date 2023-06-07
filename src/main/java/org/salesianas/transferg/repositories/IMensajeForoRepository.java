package org.salesianas.transferg.repositories;

import org.salesianas.transferg.models.MensajeForo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMensajeForoRepository extends JpaRepository<MensajeForo, Long> {

}
