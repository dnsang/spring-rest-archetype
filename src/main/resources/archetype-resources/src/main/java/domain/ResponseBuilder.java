#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain;

import ${package}.domain.APIResponse.Error;
import ${package}.exception.ErrorCode;
import ${package}.exception.ServiceErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {

  /**
   * Indicates that the request was successfully processed and the response contains the requested
   * data. This status is returned when the request is valid, and the server has successfully
   * fulfilled it.
   *
   * @param data The data or result of the successful operation.
   * @return HTTP status code 200 and a JSON response containing the data
   */
  public static <T> ResponseEntity<?> ok(T data) {
    return success(HttpStatus.OK, data);
  }

  /**
   * Indicates that the request was successfully processed and the response contains the requested
   * data. This status is returned when the request is valid, and the server has successfully
   * fulfilled it.
   *
   * @return HTTP status code 200 and an empty response
   */
  public static <T> ResponseEntity<?> ok() {
    return success(HttpStatus.NO_CONTENT, null);
  }

  /**
   * Indicates that the request involved multiple resources or operations, and the server has
   * returned a status for each part of the request. This status code is used when different
   * operations within the request have different outcomes.
   * <p>
   * This is particularly useful for batch operations where some items succeed, and others fail. The
   * response will contain detailed information about the status of each individual resource or
   * operation in the request.
   *
   * @param data A list or map containing the status and result of each individual operation within
   *             the request.
   * @return HTTP status code 207 and a JSON response containing the status and result of each
   * operation.
   */

  public static <T> ResponseEntity<?> multiStatus(T data) {
    return success(HttpStatus.MULTI_STATUS, data);
  }

  /**
   * Indicates that the request was successfully processed and a new resource has been created. This
   * status is returned when a resource is successfully created as a result of the request.
   *
   * @param data The data or details of the newly created resource.
   * @return HTTP status code 201 and a JSON response containing the details of the newly created
   * resource.
   */
  public static <T> ResponseEntity<?> created(T data) {
    return success(HttpStatus.CREATED, data);
  }

  /**
   * Indicates that the request has been accepted for processing, but the processing has not been
   * completed. This status is returned when the request has been received and understood, but the
   * outcome is not yet available.
   *
   * @param data An optional message or object providing information about the acceptance and
   *             expected processing.
   * @return HTTP status code 202 and a JSON response containing details about the accepted request
   * and its processing status.
   */
  public static <T> ResponseEntity<?> accepted(T data) {
    return success(HttpStatus.ACCEPTED, data);
  }

  public static <T> ResponseEntity<?> success(HttpStatus status, T data) {
    return Builder.builder(status).data(data).build();
  }

  /**
   * Indicates that the server cannot process the request due to invalid input from the client.
   * Examples include invalid JSON format or incorrect parameters.
   *
   * @param error Internal error code used for tracing or debugging internal server errors.
   * @return HTTP status code 400 and a json containing an internal service error code.
   */

  public static ResponseEntity<?> badRequest(ErrorCode error) {
    return fail(HttpStatus.BAD_REQUEST, error);
  }

  public static ResponseEntity<?> badRequest(ErrorCode error, String detail) {
    return fail(HttpStatus.BAD_REQUEST, error, detail);
  }

  public static ResponseEntity<?> badRequest(String detail) {
    return fail(HttpStatus.BAD_REQUEST, ServiceErrorCode.BAD_REQUEST, detail);
  }

  /**
   * Indicates that the request was rejected because the client is not authorized to access the
   * resource. This typically occurs when authentication credentials are missing, invalid, or
   * insufficient.
   *
   * @param error Inter service code used for tracing or debugging internal server issues.
   * @return HTTP status code 401 and a json response containing an internal service error code.
   */
  public static ResponseEntity<?> unauthorized(ErrorCode error) {
    return fail(HttpStatus.UNAUTHORIZED, error);
  }

  public static ResponseEntity<?> unauthorized(String detail) {
    return fail(HttpStatus.UNAUTHORIZED, ServiceErrorCode.UNAUTHORIZED, detail);
  }

  public static ResponseEntity<?> unauthorized(ErrorCode error, String detail) {
    return fail(HttpStatus.UNAUTHORIZED, error, detail);
  }

  /**
   * Indicates that the server could not find the requested resource. This status is returned when
   * the resource does not exist or the URL is incorrect.
   *
   * @param error A message or object used for tracing or debugging internal server issues.
   * @return HTTP status code 404 and a json response containing an internal service error code.
   */
  public static ResponseEntity<?> notFound(ErrorCode error) {
    return fail(HttpStatus.NOT_FOUND, error);
  }

  public static ResponseEntity<?> notFound(ErrorCode error, String detail) {
    return fail(HttpStatus.NOT_FOUND, error, detail);
  }

  public static ResponseEntity<?> notFound(String detail) {
    return fail(HttpStatus.NOT_FOUND, ServiceErrorCode.NOT_FOUND, detail);
  }

  /**
   * Indicates that the request could not be processed due to a conflict with the current state of
   * the resource. This status is returned when the request would cause a conflict, such as
   * attempting to create a resource that already exists or performing an operation that violates a
   * constraint.
   *
   * @param error A message or object used for tracing or debugging the conflict issue.
   * @return HTTP status code 409 and a JSON response containing an internal service error code,
   * providing details about the conflict and the nature of the issue.
   */
  public static ResponseEntity<?> conflict(ErrorCode error) {
    return fail(HttpStatus.CONFLICT, error);
  }

  public static ResponseEntity<?> conflict(String detail) {
    return fail(HttpStatus.CONFLICT, ServiceErrorCode.CONFLICT, detail);
  }

  public static ResponseEntity<?> conflict(ErrorCode error, String detail) {
    return fail(HttpStatus.CONFLICT, error, detail);
  }

  /**
   * Indicates that the server encountered an unexpected condition that prevented it from fulfilling
   * the request. This status is returned when there is an internal server error or an issue with
   * the server’s functionality.
   *
   * @param errorCode A message or object used for tracing or debugging internal server issues.
   * @return HTTP status code 500 and a JSON response containing an internal service error code.
   */
  public static ResponseEntity<?> internalError(ErrorCode errorCode) {
    return fail(HttpStatus.INTERNAL_SERVER_ERROR, errorCode);
  }

  public static ResponseEntity<?> internalError(String detail) {
    return fail(HttpStatus.INTERNAL_SERVER_ERROR, ServiceErrorCode.INTERNAL_SERVER_ERROR, detail);
  }

  public static ResponseEntity<?> internalError(ErrorCode errorCode, String detail) {
    return fail(HttpStatus.INTERNAL_SERVER_ERROR, errorCode, detail);
  }

  /**
   * Use to build the general fail response with specific httpStatus and internal service error
   * code
   *
   * @param httpStatus http status code 3xx 4xx 5xx
   * @param errorCode  internal service error code
   * @return HTTP status code {httpStatus} and a JSON response containing an {errorCode}
   */
  public static ResponseEntity<?> fail(HttpStatus httpStatus, ErrorCode errorCode) {
    return fail(httpStatus, errorCode, null);
  }

  public static ResponseEntity<?> fail(HttpStatus httpStatus, ErrorCode errorCode, String detail) {
    return Builder.builder(httpStatus).errorCode(errorCode)
        .detail(detail)
        .build();
  }

  static class Builder<T> {

    private HttpStatus status;
    private T data;
    private ErrorCode errorCode;
    private String detail;

    public Builder(HttpStatus status) {
      this.status = status;
    }

    public Builder<T> data(T data) {
      this.data = data;
      return this;
    }

    public Builder errorCode(ErrorCode errorCode) {
      this.errorCode = errorCode;
      return this;
    }

    public Builder detail(String detail) {
      this.detail = detail;
      return this;
    }

    public ResponseEntity<APIResponse<T>> build() {
      Error error = null;
      if (errorCode != null) {
        error = new Error();
        error.setCode(errorCode.getCode());
        error.setMessage(errorCode.getMessage());
        error.setDetail(detail);
      }
      APIResponse<T> response = new APIResponse<>(status.value(), data, error);
      return ResponseEntity.status(response.getStatus()).body(response);
    }

    public static <T> Builder<T> builder(HttpStatus status) {
      return new Builder(status);
    }
  }
}
