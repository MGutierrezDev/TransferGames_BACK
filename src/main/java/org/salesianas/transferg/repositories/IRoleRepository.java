package org.salesianas.transferg.repositories;

import org.salesianas.transferg.models.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<ERole, Long> {
	ERole findByName(String name);
}
