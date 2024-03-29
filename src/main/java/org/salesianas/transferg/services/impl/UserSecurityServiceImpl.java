package org.salesianas.transferg.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.salesianas.transferg.exceptions.EmailInvalidException;
import org.salesianas.transferg.exceptions.UserNotFoundException;
import org.salesianas.transferg.models.ERole;
import org.salesianas.transferg.models.UserSecurity;
import org.salesianas.transferg.models.dto.UserDTO;
import org.salesianas.transferg.repositories.IRoleRepository;
import org.salesianas.transferg.repositories.IUserSecurityRepository;
import org.salesianas.transferg.services.IUserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserSecurityServiceImpl implements IUserSecurityService {
	
	@Autowired
	private IUserSecurityRepository userRepository;
	
	@Autowired
	private IRoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;
	
    public boolean isEmailAlreadyExists(String email) {
        return userRepository.existsByEmail(email);
    }
	
	public UserSecurity convertToEntity(UserDTO userDto) {
		return modelMapper.map(userDto, UserSecurity.class);
	}

	public UserDTO convertToDto(UserSecurity user) {
		return modelMapper.map(user, UserDTO.class);
	}

	public List<UserDTO> usersListDto(List<UserSecurity> users){
		List<UserDTO> usersResponse = new ArrayList<>();
		for (UserSecurity user : users) {
			usersResponse.add(new UserDTO(user));
		}

		return usersResponse;
	}


	@Override
	public UserSecurity saveUser(UserSecurity user) throws Exception {
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new EmailInvalidException();
		}
		return userRepository.save(user);
	}

	@Override
	public UserSecurity updateUser(Long idUser, UserSecurity newUser) throws Exception {
		  String encodedPassword = new BCryptPasswordEncoder().encode(newUser.getPassword());
		  UserSecurity user = getUserById(idUser);
		  ERole rolAdmin = roleRepository.findById((long) 1).get();
		  ERole rolUser = roleRepository.findById((long) 2).get();
		  user.setName(newUser.getName());
		  user.setEmail(newUser.getEmail());
		  user.setPassword(encodedPassword);
		  if(newUser.getPassword()!=null) {
			  user.setPassword(encodedPassword);
		  }
		  if(newUser.getRoleId()!= null && newUser.getRoleId().getName().equals("ADMIN")) {
			  user.setRoleId(rolAdmin);
		  } else{
			  user.setRoleId(rolUser);
		  }
		  user.setImage(newUser.getImage());
		  user.setMensajes(newUser.getMensajes());
		  user.setRespuestas(newUser.getRespuestas());
		  user.setJuegos(newUser.getJuegos());
		  return userRepository.save(user);
	}

	@Override
	public void deleteUser(UserSecurity user) {
		userRepository.delete(user);
		
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
		
	}

	@Override
	public UserSecurity getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
	}

	@Override
	public List<UserDTO> getAllUsers() throws Exception {
		List<UserSecurity> users = userRepository.findAll();
		return usersListDto(users);
	}

	@Override
	public void deleteUserById(Long id) throws Exception {
		userRepository.deleteById(id);
	}

	@Override
	public UserSecurity getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	@Override
	public UserDTO saveImageUser(MultipartFile img, UserSecurity user) throws IOException {
		byte[] bytes = null;
		if (!img.isEmpty()) {
			bytes = img.getBytes();
		}
		user.setImage(bytes);
		userRepository.save(user);
		return convertToDto(user);
	}

	@Override
	public String checkValidEmail(UserSecurity user) {
		UserSecurity userEmail = userRepository.findByEmail(user.getEmail());
		if (userEmail != null && !Objects.equals(user.getId(), userEmail.getId())) {
			throw new EmailInvalidException();
		}
		return userEmail.getEmail();
	}
	
}
