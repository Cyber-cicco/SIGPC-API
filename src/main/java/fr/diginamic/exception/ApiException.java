package fr.diginamic.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
  public ApiException() {
    super("Exception fonctionnelle générique - Contactez l'administrateur");
  }

  public ApiException(String message) {
    super(message);
  }
}
