package org.salesianas.transferg.security.impl;

import java.util.ArrayList;
import java.util.List;

import org.salesianas.transferg.models.ERole;
import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.repositories.IRoleRepository;
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
	
	@Autowired
	IRoleRepository roleRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		UserSecurity usuario = usuarioRepository
			.findOneByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("El usuario con email " + email + " no existe."));	
		ERole role = new ERole();
		if(usuario.isAdmin()) {
			role = roleRepository.findByName("ADMIN");
		}else{
			role = roleRepository.findByName("USER");
		}

        usuario.setRole(role);
		return  new UserDetailImpl(usuario);
	}
}