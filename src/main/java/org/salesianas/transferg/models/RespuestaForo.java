package org.salesianas.transferg.models;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Data
public class RespuestaForo {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idRespuesta;
  
  private String titulo;
  
  private String respuesta;
  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern="yyyy/MM/dd")
  private Date date;
  
  @ManyToOne
  @JsonIgnore
  private MensajeForo mensajeForo;
  
  @ManyToOne
  @JsonIgnore
  private UserSecurity user;
}
