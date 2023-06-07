package org.salesianas.transferg.security;

import java.util.Collections;

import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.services.impl.UserSecurityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;



@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired private UserSecurityServiceImpl userService;

    /**
     * Carga el usuario por el email
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
    	UserSecurity userRes = userService.getUserByEmail(email);
        
        
        if(userRes==null) {
            throw new UsernameNotFoundException("Could not findUser with email = " + email);
        }
        
        return new org.springframework.security.core.userdetails.User(
                email,
                userRes.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+userRes.getRoleId().getName())));
    }
}