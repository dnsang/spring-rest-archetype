#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.jetty.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APIResponse<T> {

  @JsonIgnore
  protected Integer httpStatus;
  protected Error error;
  protected T data;

  public static <T> APIResponse<T> success(T data) {
    return response(HttpStatus.OK_200, data, null, null);
  }

  public static <T> APIResponse<T> fail(Integer httpStatus, Integer internalErrorCode,
      String message) {
    return response(httpStatus, null, internalErrorCode, message);
  }

  public static <T> APIResponse<T> badRequest(Integer errorCode, String message) {
    return fail(HttpStatus.BAD_REQUEST_400, errorCode, message);
  }

  public static <T> APIResponse<T> notFound(Integer errorCode, String message) {
    return fail(HttpStatus.NOT_FOUND_404, errorCode, message);
  }

  public static <T> APIResponse<T> internalError(Integer errorCode, String message) {
    return fail(HttpStatus.INTERNAL_SERVER_ERROR_500, errorCode, message);
  }

  public static <T> APIResponse response(int httpStatus, T data, Integer errorCode,
      String errorMessage) {
    if (HttpStatus.isSuccess(httpStatus)) {
      return APIResponse.<T>builder()
          .data(data)
          .build();
    } else {
      return APIResponse.<T>builder()
          .error(new Error(errorCode, errorMessage))
          .build();
    }

  }

  public ResponseEntity<APIResponse<T>> toResponseEntity() {
    return ResponseEntity.status(this.getHttpStatus()).body(this);
  }

  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  static
  class Error {

    protected int code;
    protected String message;
  }
}
