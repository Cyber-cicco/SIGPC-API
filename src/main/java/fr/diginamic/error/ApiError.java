package fr.diginamic.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {
  /** Timestamp de l'erreur */
  @Builder.Default
  private long timestamp = new Date().getTime();

  /** Message d'erreur */
  private String errorMsg;

  /** Méthode de la requête dont provient l'erreur */
  private String method;

  /** URL de la requête dont provient l'erreur */
  private String url;

  /** Erreurs de validation */
  private Map<String, String> validationErrors;

  /** Constructeur */
  public ApiError(String method, String errorMsg, String url) {
    this.method = method;
    this.errorMsg = errorMsg;
    this.url = url;
  }
}
