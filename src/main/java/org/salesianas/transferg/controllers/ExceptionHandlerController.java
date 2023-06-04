package org.salesianas.transferg.controllers;

import java.time.LocalDateTime;
import org.salesianas.transferg.exceptions.EmailInvalidException;
import org.salesianas.transferg.exceptions.MensajeNotFoundException;
import org.salesianas.transferg.exceptions.MensajeOnUserNotFoundException;
import org.salesianas.transferg.exceptions.PasswordInvalidException;
import org.salesianas.transferg.exceptions.RespuestaNotFoundException;
import org.salesianas.transferg.exceptions.RespuestaOnMensajeNotFoundException;
import org.salesianas.transferg.exceptions.RespuestaOnUserNotFoundException;
import org.salesianas.transferg.exceptions.UserNotFoundException;
import org.salesianas.transferg.exceptions.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

  //****************EXCEPCIONES DE MENSAJE********************
  @ExceptionHandler(MensajeNotFoundException.class)
  public ResponseEntity<ErrorMessage> mensajeNotFoundException(MensajeNotFoundException exception){
    ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND, exception.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }
  @ExceptionHandler(MensajeOnUserNotFoundException.class)
  public ResponseEntity<ErrorMessage> mensajeOnUserNotFoundException(MensajeOnUserNotFoundException exception){
    ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND, exception.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }
  
  //****************EXCEPCIONES DE RESPUESTA********************
  @ExceptionHandler(RespuestaNotFoundException.class)
  public ResponseEntity<ErrorMessage> respuestaNotFoundException(RespuestaNotFoundException exception){
    ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND, exception.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }
  @ExceptionHandler(RespuestaOnUserNotFoundException.class)
  public ResponseEntity<ErrorMessage> respuestaOnUserNotFoundException(RespuestaOnUserNotFoundException exception){
    ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND, exception.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }
  @ExceptionHandler(RespuestaOnMensajeNotFoundException.class)
  public ResponseEntity<ErrorMessage> respuestaOnMensajeNotFoundException(RespuestaOnMensajeNotFoundException exception){
    ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND, exception.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }
  
  //****************EXCEPCIONES DE USER********************
  //USER NO ENCONTRADO
  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorMessage> userNotFoundException(UserNotFoundException exception){
    ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND, exception.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }
  
  //USER PASSWORD INVALID
  @ExceptionHandler(PasswordInvalidException.class)
  public ResponseEntity<ErrorMessage> passwordInvalidException(PasswordInvalidException exception){
    ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.CONFLICT, exception.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  } 
  
  //****************EXCEPCIONES DE EMAIL********************
  @ExceptionHandler(EmailInvalidException.class)
  public ResponseEntity<ErrorMessage> emailInvalidException(EmailInvalidException exception){
    ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.CONFLICT, exception.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }
}
