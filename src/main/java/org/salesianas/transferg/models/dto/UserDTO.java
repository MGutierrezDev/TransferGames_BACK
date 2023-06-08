package org.salesianas.transferg.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.salesianas.transferg.models.ERole;
import org.salesianas.transferg.models.UserSecurity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private Long id;
	
	private String name;
	@NotBlank(message = "Email is obligatory")
	@Email(message = "The email is incorrect")
	private String email;
	
	private ERole role;
	
	private byte[] imagen;
	
	public UserDTO(UserSecurity user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.role = user.getRoleId();
	}
}
