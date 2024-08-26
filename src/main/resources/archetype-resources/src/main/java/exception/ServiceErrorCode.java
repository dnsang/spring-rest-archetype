#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.exception;

import java.util.HashMap;
import java.util.HashSet;
import org.slf4j.LoggerFactory;

/**
 * The {@code ServiceErrorCode} class defines a set of error codes and corresponding error messages
 * used across different services in the application. Each error code is represented as a numeric value
 * starting from 10000 and incrementing for different error scenarios. Each microservice has a distinct prefix
 * to distinguish its errors from those of other services.
 *
 * <p>Each error code is associated with a specific error message that describes the error in a human-readable
 * format.
 *
 * <p>Example usage:
 * <pre>
 *     // Example of using a service error code
 *     ServiceErrorCode code = ServiceErrorCode.UNKNOWN_EXCEPTION;
 * </pre>
 *
 * <p>Note: Error codes are designed to be unique across services by incorporating a service-specific prefix.
 * Example :
 * <pre>
 *   Authentication Service error code start from 10xxx
 *   Order Service error code start from 20xxx
 *   Payment Service error code start from 30xxx
 * </pre>
 * */
public enum ServiceErrorCode implements ErrorCode{
  BAD_REQUEST(10400,"The server could not understand the request due to invalid syntax."),
  UNAUTHORIZED(10401,"Authentication is required or has failed."),
  FORBIDDEN(10403,"The server understood the request but refuses to authorize it."),
  NOT_FOUND(10404,"The requested resource could not be found."),
  CONFLICT(10409,"Request could not be processed due to resource state conflict"),
  METHOD_NOT_ALLOWED(10405,"The request method is not supported for the requested resource."),
  INTERNAL_SERVER_ERROR(10500,"The server encountered an error and couldn't complete the request."),
  BAD_GATEWAY(10502,"Invalid response received from an upstream server or gateway."),
  SERVICE_UNAVAILABLE(10503,"The server is temporarily unavailable due to overload or maintenance."),
  GATEWAY_TIMEOUT(10504,"The upstream server failed to respond in a timely manner."),
  UNKNOWN_EXCEPTION(11000,"An Unknown Error Occurred"),
  DATABASE_CONNECTION_FAILED(11200,"Can't Connect To Database"),
  DATABASE_CONNECTION_TIMEOUT(11201, "Database Connection Timeout");
  protected int code;
  protected String message;

  ServiceErrorCode(int code,String message){
    this.code = code;
    this.message = message;
  }

  @Override
  public int getCode() {
    return this.code;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  static {
    HashSet<Integer> values  = new HashSet<>();
    for(ServiceErrorCode code : ServiceErrorCode.values()){
      if(!values.add(code.getCode())){
        LoggerFactory.getLogger(ServiceErrorCode.class).error("Duplicate ServiceErrorCode Code:" + code.getCode() + " Message:" + code.getMessage());
      }
    }
  }

}
