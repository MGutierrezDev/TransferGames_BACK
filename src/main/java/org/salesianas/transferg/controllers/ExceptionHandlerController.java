package org.salesianas.transferg.controllers;

import java.time.LocalDateTime;

import org.salesianas.transferg.exceptions.EmailInvalidException;
import org.salesianas.transferg.exceptions.EmailNotFoundException;
import org.salesianas.transferg.exceptions.JuegoNotFoundException;
import org.salesianas.transferg.exceptions.LoginInvalidException;
import org.salesianas.transferg.exceptions.MensajeNotFoundException;
import org.salesianas.transferg.exceptions.MensajeOnUserNotFoundException;
import org.salesianas.transferg.exceptions.PasswordInvalidException;
import org.salesianas.transferg.exceptions.RespuestaNotFoundException;
import org.salesianas.transferg.exceptions.RespuestaOnMensajeNotFoundException;
import org.salesianas.transferg.exceptions.RespuestaOnUserNotFoundException;
import org.salesianas.transferg.exceptions.UserEmailNotFoundException;
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
  
  //USER EMAIL NO ENCONTRADO
  @ExceptionHandler(UserEmailNotFoundException.class)
  public ResponseEntity<ErrorMessage> userNotFoundException(UserEmailNotFoundException exception){
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
  //EMAIL YA EXISTE EN LA BBDD
  @ExceptionHandler(EmailInvalidException.class)
  public ResponseEntity<ErrorMessage> emailInvalidException(EmailInvalidException exception){
    ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.CONFLICT, exception.getMessage());
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }
  
  //EMAIL NO EXISTE
  @ExceptionHandler(EmailNotFoundException.class)
  public ResponseEntity<ErrorMessage> emailInvalidException(EmailNotFoundException exception){
    ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND, exception.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }
  
  //*****************EXCEPCIONES DE JUEGO*******************
  @ExceptionHandler(JuegoNotFoundException.class)
  public ResponseEntity<ErrorMessage> juegoNotFoundException(JuegoNotFoundException exception){
    ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.NOT_FOUND, exception.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }
  
  //******************EXCEPCIONES DE LOGIN********************
  @ExceptionHandler(LoginInvalidException.class)
  public ResponseEntity<ErrorMessage> loginInvalidException(LoginInvalidException exception){
    ErrorMessage error = new ErrorMessage(LocalDateTime.now(), HttpStatus.BAD_REQUEST, exception.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }
}
