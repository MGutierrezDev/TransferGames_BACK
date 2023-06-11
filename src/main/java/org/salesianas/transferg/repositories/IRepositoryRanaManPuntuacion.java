package org.salesianas.transferg.repositories;

import org.salesianas.transferg.models.RanaManPuntuacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryRanaManPuntuacion extends JpaRepository<RanaManPuntuacion, Long> {

}
