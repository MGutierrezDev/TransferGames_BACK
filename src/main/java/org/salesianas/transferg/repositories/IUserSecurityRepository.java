package org.salesianas.transferg.repositories;

import org.salesianas.transferg.models.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserSecurityRepository extends JpaRepository<UserSecurity, Long> {
	public UserSecurity findByEmail(String email);
}
