package org.salesianas.transferg.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
public class Juego {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  
  private String nombre;
  private String descripcion;
  private byte[] imagen;
  
  @ManyToMany (mappedBy = "juegos")
  @JsonIgnoreProperties("juegos")  
  private List <UserSecurity> usuarios = new ArrayList<>();
}
