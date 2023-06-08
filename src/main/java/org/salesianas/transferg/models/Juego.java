package org.salesianas.transferg.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Type;

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
  
  @Column(unique = true)
  private String direccion;

  @Lob
  @Type(type = "org.hibernate.type.BinaryType")
  @Column(columnDefinition = "LONGBLOB")
  private byte[] image;
  
  @ManyToMany (mappedBy = "juegos")
  @JsonIgnoreProperties("juegos")  
  private List <UserSecurity> usuarios = new ArrayList<>();
}
