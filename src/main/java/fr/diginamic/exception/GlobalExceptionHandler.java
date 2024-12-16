package fr.diginamic.exception;

import static fr.diginamic.shared.ResponseUtil.error;

import com.fasterxml.jackson.databind.JsonMappingException;
import fr.diginamic.error.ApiError;
import fr.diginamic.shared.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import jakarta.validation.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<ApiResponse<ApiError>> handleMethodArgumentNotValidException(
      MethodArgumentNotValidException ex, HttpServletRequest request) {

    LOGGER.error("an error occurred {}", ex.getMessage());
    System.err.println("ERREUR DE VALIDATION");

    ApiError apiError =
        new ApiError(request.getMethod(), "Erreur de validation", request.getServletPath());

    Map<String, String> validationErrors = new HashMap<>();

    ex.getBindingResult()
        .getFieldErrors()
        .forEach(
            (fieldError) ->
                validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage()));
    apiError.setValidationErrors(validationErrors);
    ApiResponse<ApiError> apiResponse = error("Données invalides", apiError);

    return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse<ApiError>> handleResourceNotFoundException(
      ResourceNotFoundException ex, HttpServletRequest request) {
    String message = ex.getMessage();
    ApiError apiError = new ApiError(request.getMethod(), message, request.getRequestURI());
    ApiResponse<ApiError> apiResponse = error(message, apiError);
    return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
  }
  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ApiResponse<ApiError>> handleUnauthorizedException(UnauthorizedException ex) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error("unauthorized", null));
  }
  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ApiResponse<ApiError>> handleValidationException(ValidationException ex, HttpServletRequest req) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error("Validation error", ApiError.builder()
            .errorMsg("Invalid request")
            .url(req.getRequestURI())
            .method(req.getMethod())
            .validationErrors(Map.of("error", ex.getMessage()))
            .build()));
  }
  @ExceptionHandler(JsonMappingException.class)
  public ResponseEntity<ApiResponse<ApiError>> handleJsonMappingException(JsonMappingException ex, HttpServletRequest req) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error("Malformed request", ApiError.builder()
            .errorMsg("Could not parse JSON")
            .url(req.getRequestURI())
            .method(req.getMethod())
            .validationErrors(Map.of("error", ex.getMessage()))
            .build()));
  }


  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ApiResponse<ApiError>> handleAPIException(
      ApiException ex, HttpServletRequest request) {
    String message = ex.getMessage();
    ApiError apiError = new ApiError(request.getMethod(), message, request.getServletPath());
    ApiResponse<ApiError> apiResponse = error("Données invalides", apiError);
    return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
  }
}
