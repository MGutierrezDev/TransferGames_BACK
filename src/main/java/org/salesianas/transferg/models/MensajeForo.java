package org.salesianas.transferg.models;

import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Entity
@Data
public class MensajeForo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMensaje;	
	private String asunto;
	private String texto;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy/MM/dd")
	private Date date;
	
	@ManyToOne
	@JsonIgnore
	private UserSecurity user;
	
	@OneToMany(mappedBy="mensajeForo")
	@JsonIgnoreProperties("mensajeForo")
	private List<RespuestaForo> respuestas;
}
