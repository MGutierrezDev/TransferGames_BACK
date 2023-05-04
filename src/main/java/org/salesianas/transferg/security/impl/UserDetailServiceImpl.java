package org.salesianas.transferg.security.impl;

import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.repositories.IUserSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	IUserSecurityRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		UserSecurity usuario = usuarioRepository
			.findOneByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe."));		
		return  new UserDetailImpl(usuario);
	}
}