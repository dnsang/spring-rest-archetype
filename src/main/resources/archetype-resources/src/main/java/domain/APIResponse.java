#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain;

import ${package}.exception.ErrorCode;
import ${package}.exception.ServiceErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Represents a standardized response for all API endpoints. This class provides a consistent
 * structure for both successful and error responses across the API.
 *
 * <p>For successful responses, the structure typically includes:</p>
 * <ul>
 *   <li><strong>status:</strong> The HTTP status code representing success (e.g., 200 OK, 201 Created). </li>*
 *   <li><strong>data:</strong> The data resulting from the request</li>
 * </ul>
 *
 * <p>For error responses, the structure typically includes:</p>
 * <ul>
 *   <li><strong>status:</strong> The HTTP status code representing the error (e.g., 400 Bad Request, 500 Internal Server Error)</li>
 *   <li><strong>error:</strong> An instance of {@link ErrorCode} detailing the internal error code.</li>
 * </ul>
 *
 * @param <T> The type of the response data object.
 * @Note: The error response adheres to the RFC 9457 standard.
 ***/


@Data
@NoArgsConstructor
public class APIResponse<T> {

  int status;
  protected T data;
  protected Error error;

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  static class Error {

    int code;
    String message;
    String detail;
  }

  APIResponse(int status, T data, Error error) {
    this.status = status;
    this.data = data;
    this.error = error;
  }
}
