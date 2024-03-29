package org.salesianas.transferg.services;

import java.io.IOException;
import java.util.List;

import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.models.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IUserSecurityService {
	public UserSecurity saveUser(UserSecurity user)throws Exception;
	public UserSecurity updateUser(Long idUser, UserSecurity newUuser) throws Exception;
	public void deleteUser(UserSecurity user) throws Exception;
	public void deleteById(Long id) throws Exception;
	public UserSecurity getUserById(Long id) throws Exception;
	public List<UserDTO> getAllUsers() throws Exception;
	public void deleteUserById(Long id) throws Exception;
	public UserSecurity getUserByEmail(String email);
	public UserDTO saveImageUser(MultipartFile img, UserSecurity user) throws IOException;
	public String checkValidEmail(UserSecurity user);
}
