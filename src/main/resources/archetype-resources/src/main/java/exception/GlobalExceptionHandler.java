#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.exception;

import ${package}.domain.APIResponse;
import ${package}.domain.ResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public final class GlobalExceptionHandler {
 @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleException(Exception e){
   return ResponseBuilder.internalError(e.getMessage());
 }
 @ExceptionHandler(ApplicationException.class)
 public ResponseEntity<?> handleApplicationException(ApplicationException ex){
  return ResponseBuilder.fail(HttpStatus.INTERNAL_SERVER_ERROR,ex.getErrorCode(), ex.getMessage());
 }

}
