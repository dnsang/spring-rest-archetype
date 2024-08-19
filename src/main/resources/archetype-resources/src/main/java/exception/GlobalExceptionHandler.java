#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.exception

import dev.sang.spring.archetype.domain.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(Exception.class)
  public ResponseEntity<APIResponse<String>> handleException(Exception e){
     APIResponse response=  APIResponse.<String>internalError(InternalError.UNKNOWN_EXCEPTION,e.getMessage());
     return response.toResponseEntity();
 }

}
