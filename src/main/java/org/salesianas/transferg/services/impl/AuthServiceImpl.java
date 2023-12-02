package org.salesianas.transferg.services.impl;

import java.util.Collections;
import java.util.Map;

import org.salesianas.transferg.exceptions.BlankNameException;
import org.salesianas.transferg.exceptions.EmailFormatoInvalidException;
import org.salesianas.transferg.exceptions.PasswordInvalidException;
import org.salesianas.transferg.models.ERole;
import org.salesianas.transferg.models.LoginRequest;
import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.repositories.IRoleRepository;
import org.salesianas.transferg.security.JWTUtil;
import org.salesianas.transferg.services.IAuthService;
import org.salesianas.transferg.services.IUserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private IUserSecurityService userService;
	
	@Autowired
	private IRoleRepository roleRepository;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Override
	public Map<String, Object> registerUser(UserSecurity user) throws Exception {
		if(user.getPassword().length()<6) {
			throw new PasswordInvalidException();
		}
		if (user.getName().length() < 2) {
		    throw new BlankNameException();
		}
	    if (!user.getEmail().matches("^.[^\\s@]+@([^\\s@.,]+\\.)+[a-zA-Z]{2,}$")) {
	        throw new EmailFormatoInvalidException();
	    }
		ERole rolAdmin = roleRepository.findByName("ADMIN");
		ERole rolUser = roleRepository.findByName("USER");
		if("ADMIN".equals(user.getRoleId().getName())) {
			user.setRoleId(rolAdmin);
		}
		else {
			user.setRoleId(rolUser);
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		UserSecurity userCreado = userService.saveUser(user);
		return Collections.singletonMap("jwt_token", jwtUtil.generateToken(userCreado.getRoleId().getName(), userCreado.getId(), userCreado.getEmail()));
	}

	@Override
	public Map<String, Object> loginUser(LoginRequest userLogin) {
		UserSecurity user = userService.getUserByEmail(userLogin.getEmail());
		String rol = user.getRoleId().getName();
		String token = jwtUtil.generateToken(rol, user.getId(), user.getEmail());
		return Collections.singletonMap("jwt_token", token);
	}

}
