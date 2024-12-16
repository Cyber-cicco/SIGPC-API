package fr.diginamic.shared;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
  private String status;
  private String message;
  //  private boolean success;
  private T data;
  private Object metadata;

  //  public ApiResponse(String status,String message, T data,Object metadata) {
  //    this.status = status;
  //      this.message = message;
  ////    this.success = success;
  //      this.data = data;
  //      this.metadata = metadata;
  //  }

}
