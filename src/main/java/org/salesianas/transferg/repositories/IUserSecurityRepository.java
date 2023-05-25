package org.salesianas.transferg.repositories;

import java.util.Optional;

import org.salesianas.transferg.models.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserSecurityRepository extends JpaRepository<UserSecurity, Long> {
	Optional<UserSecurity> findOneByEmail(String email);
	UserSecurity findByEmail(String email);
}
