package fr.diginamic.shared;

public class ResponseUtil {

  public static <T> ApiResponse<T> success(String message, T data, Object metadata) {
    return new ApiResponse<>("SUCCESS", message, data, metadata);
  }

  public static <T> ApiResponse<T> success(String message, T data) {
    return new ApiResponse<>("SUCCESS", message, data, null);
  }

  public static <T> ApiResponse<T> error(String message, T data) {
    return new ApiResponse<>("ERROR", message, data, null);
  }
}
