package org.salesianas.transferg.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
public class UserSecurity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "^.[^\\d]{2,40}$")
	private String name;
	
	@Column(unique = true)
	@NotBlank(message = "Email is mandatory")
	@Email(message = "The email is incorrect")
	private String email;
	
	@NotBlank
	@Size(min = 6)
	private String password;
	
	@ManyToOne()
	@JoinColumn(name = "role_id")
	private ERole roleId;
	
	@Lob
	@Type(type = "org.hibernate.type.BinaryType")
	@Column(columnDefinition = "LONGBLOB")
	private byte[] image;

	@OneToMany(fetch=FetchType.LAZY, mappedBy="user")
	@JsonIgnoreProperties({"user"})
	private List <MensajeForo> mensajes = new ArrayList<>();
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="user")
    @JsonIgnoreProperties({"user"})
    private List <RespuestaForo> respuestas = new ArrayList<>();
    
    @ManyToMany
    @JoinTable(name = "user_juegos", joinColumns = @JoinColumn (name = "user_id"), inverseJoinColumns = @JoinColumn (name = "juego_id"))
    @JsonIgnoreProperties("user")  
    private List <Juego> juegos= new ArrayList<>();

    public boolean isAdmin() {
        return roleId != null && roleId.getName().equals("ADMIN");
    }
    
	
}
