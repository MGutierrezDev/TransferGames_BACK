package org.salesianas.transferg.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
public class RanaManPuntuacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long puntuacion;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="ranaManPuntuacion")
	@JsonIgnoreProperties({"ranaManPuntuacion"})
	private List<UserSecurity> users = new ArrayList<>();
}
