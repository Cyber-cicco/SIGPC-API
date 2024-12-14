package fr.diginamic.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class APIError {
  /** Timestamp de l'erreur */
  private long timestamp = new Date().getTime();

  /** Message d'erreur */
  private String errorMsg;

  /** Méthode de la requête dont provient l'erreur */
  private String method;

  /** URL de la requête dont provient l'erreur */
  private String url;

  private Map<String, String> validationErrors;

  public APIError(String method, String errorMsg, String url) {
    this.method = method;
    this.errorMsg = errorMsg;
    this.url = url;
  }
}
