package fr.diginamic.controller;

import com.fasterxml.jackson.databind.JsonMappingException;
import fr.diginamic.exception.UnauthorizedException;
import fr.diginamic.shared.ErrorMessage;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorMessage> handleEntityNotFoundException(ValidationException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(ex.getMessage()));
    }
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorMessage> handleUnauthorizedException(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorMessage("Unauthorized"));
    }
    @ExceptionHandler(JsonMappingException.class)
    public ResponseEntity<ErrorMessage> handleMalformedRequest(JsonMappingException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(ex.getMessage()));
    }
}
