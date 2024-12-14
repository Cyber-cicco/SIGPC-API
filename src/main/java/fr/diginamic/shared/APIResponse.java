package fr.diginamic.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {
  private String message;
  private boolean success;
  private T data;

  public APIResponse(String message, boolean success) {
    this.message = message;
    this.success = success;
  }
}
