package org.salesianas.transferg.security.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.salesianas.transferg.models.UserSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDetailImpl  implements UserDetails {

	private final UserSecurity usuario;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    List<GrantedAuthority> authorities = new ArrayList<>();
	    
	    // Agregar el rol del usuario como una instancia de GrantedAuthority
	    if (usuario.getRole() != null) {
	        authorities.add(new SimpleGrantedAuthority(usuario.getRole().getName()));
	    }
	    
	    return authorities;
	}

	@Override
	public String getPassword() {
		return usuario.getPassword();
	}

	@Override
	public String getUsername() {
		return usuario.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public String getName() {
		return usuario.getName();
	}


}
