package fr.diginamic.exception;

import lombok.Getter;

@Getter
public class APIException extends RuntimeException {
  public APIException() {
    super("Exception fonctionnelle générique - Contactez l'administrateur");
  }

  public APIException(String message) {
    super(message);
  }
}
