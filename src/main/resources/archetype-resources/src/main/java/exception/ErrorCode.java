#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.exception;


public interface ErrorCode {

  int getCode();

  String getMessage();

}