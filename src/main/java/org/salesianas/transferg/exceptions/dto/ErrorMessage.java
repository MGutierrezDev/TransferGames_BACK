package org.salesianas.transferg.exceptions.dto;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {

  @JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss")
  private LocalDateTime fecha;
  private HttpStatus status;
  private String message;
}
