package org.salesianas.transferg.models;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Perro {
	@Id
	private Long id;
	private String name;
}
