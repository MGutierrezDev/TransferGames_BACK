package org.salesianas.transferg.services;

import java.util.Map;

import org.salesianas.transferg.models.LoginRequest;
import org.salesianas.transferg.models.UserSecurity;
import org.springframework.stereotype.Service;

@Service
public interface IAuthService {
	public Map<String, Object> registerUser(UserSecurity user) throws Exception;
	public Map<String, Object> loginUser(LoginRequest userLogin) throws Exception;
}
