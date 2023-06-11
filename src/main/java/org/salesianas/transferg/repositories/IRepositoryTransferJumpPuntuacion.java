package org.salesianas.transferg.repositories;

import org.salesianas.transferg.models.TransferJumpPuntuacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryTransferJumpPuntuacion extends JpaRepository<TransferJumpPuntuacion, Long> {

}
