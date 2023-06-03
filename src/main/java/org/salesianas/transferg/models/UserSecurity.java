package org.salesianas.transferg.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
public class UserSecurity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("users")
    private List<ERole> roles = new ArrayList<>();

	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	@JsonIgnoreProperties({"user"})
	private List <MensajeForo> mensajes = new ArrayList<>();
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    @JsonIgnoreProperties({"user"})
    private List <RespuestaForo> respuestas = new ArrayList<>();
    
    public boolean isAdmin() {
        return roles.stream().anyMatch(role -> role.getName().equals("ADMIN"));
    }
}
