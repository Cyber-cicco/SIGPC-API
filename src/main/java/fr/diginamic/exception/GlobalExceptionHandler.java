package fr.diginamic.exception;

import fr.diginamic.error.APIError;
import fr.diginamic.shared.APIResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  // TODO : enlever DateTimeParseException.class si non utilisée
  //  (non utilisée pour l'instant)
  @ExceptionHandler({MethodArgumentNotValidException.class, DateTimeParseException.class})
  public ResponseEntity<APIResponse<APIError>> handleMethodArgumentNotValidException(
      //  public ResponseEntity<String> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex, HttpServletRequest request) {

    LOGGER.error("an error occurred {}", ex.getMessage());
    System.err.println("ERREUR DE VALIDATION");

    APIResponse<APIError> apiResponse = new APIResponse<>("Données invalides", false);
    APIError apiError =
        new APIError(request.getMethod(), "Erreur de validation", request.getServletPath());

    Map<String, String> validationErrors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String fiedErrorMessage = error.getDefaultMessage();
              validationErrors.put(fieldName, fiedErrorMessage);
            });

    //    result
    //        .getFieldErrors()
    //        .forEach(
    //            (fieldError) -> {
    //              validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
    //            });
    apiError.setValidationErrors(validationErrors);
    apiResponse.setData(apiError);

    //    return ResponseEntity.badRequest().body(apiResponse);
    return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);

    //    return ResponseEntity.badRequest().body("ERREUR DE VALIDATION");
    //    return new ResponseEntity<>("ERREUR DE VALIDATION", HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<APIResponse<?>> handleResourceNotFoundException(
      ResourceNotFoundException ex) {
    String message = ex.getMessage();
    APIResponse<?> reponseAPI = new APIResponse<>(message, false);
    return new ResponseEntity<>(reponseAPI, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(APIException.class)
  public ResponseEntity<APIResponse<?>> handleAPIException(APIException ex) {
    String message = ex.getMessage();
    APIResponse<?> reponseAPI = new APIResponse<>(message, false);
    return new ResponseEntity<>(reponseAPI, HttpStatus.BAD_REQUEST);
  }
}

// @ExceptionHandler(MethodArgumentNotValidException.class)
// public ResponseEntity<APIError> handleMethodArgumentNotValidException(
//        MethodArgumentNotValidException ex, HttpServletRequest request) {
//    APIError erreurApi = new APIError(400, "Erreur de validation", request.getServletPath());
//
//    BindingResult resultat = ex.getBindingResult();
//    Map<String, String> erreursValidation = new HashMap<>();
//    resultat
//            .getFieldErrors()
//            .forEach(
//                    (fieldError) -> {
//                        erreursValidation.put(fieldError.getField(),
// fieldError.getDefaultMessage());
//                    });
//    erreurApi.setValidationErrors(erreursValidation);
//
//    return ResponseEntity.badRequest().body(erreurApi);
// }
