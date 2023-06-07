package org.salesianas.transferg.repositories;

import org.salesianas.transferg.models.RespuestaForo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRespuestaForoRepository extends JpaRepository<RespuestaForo, Long> {

}
