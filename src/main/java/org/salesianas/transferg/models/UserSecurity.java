package org.salesianas.transferg.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
	
	@NotBlank
	@Size(min = 6)
	private String password;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade= {CascadeType.ALL})
	ERole role = new ERole();

	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	@JsonIgnoreProperties({"user"})
	private List <MensajeForo> mensajes = new ArrayList<>();
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    @JsonIgnoreProperties({"user"})
    private List <RespuestaForo> respuestas = new ArrayList<>();
    
    @OneToMany(fetch = FetchType.EAGER, cascade= {CascadeType.ALL})
    @JsonIgnoreProperties({"user"})
    private List <Juego> listaJuegos = new ArrayList<>();

    public boolean isAdmin() {
        return role != null && role.getName().equals("ADMIN");
    }
    
	
}
