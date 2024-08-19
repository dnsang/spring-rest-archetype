#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.exception


import java.util.HashMap;
import lombok.Getter;

@Getter
public class InternalError {

  public static final int UNKNOWN_EXCEPTION = 50000;
  public static final int DATABASE_CONNECTION_FAILED = 50001;
  public static final int DATABASE_CONNECTION_TIMEOUT = 50002;
  public static final int UNCAUGHT_EXCEPTION = 50010;
  public static final int EXTERNAL_SERVICE_FAILED = 50020;


  private static final HashMap<Integer, ErrorDescription> mapErrorCode = new HashMap();

  static {
    for (ErrorDescription errorCode : ErrorDescription.values()) {
      mapErrorCode.put(errorCode.code, errorCode);
    }
  }

  public enum ErrorDescription {
    UNKNOWN_EXCEPTION(InternalError.UNKNOWN_EXCEPTION, "Unknown Exception"),
    DB_CONNECTION_FAILED(InternalError.DATABASE_CONNECTION_FAILED, "Database Connection Failed"),
    DB_CONNECTION_TIMEOUT(InternalError.DATABASE_CONNECTION_TIMEOUT, "Database Connection Failed"),
    UNCAUGHT_EXCEPTION(InternalError.UNCAUGHT_EXCEPTION, "Uncaught Exception"),
    EXTERNAL_SERVICE_FAILED(InternalError.EXTERNAL_SERVICE_FAILED, "External Service Failed");


    private final int code;
    private final String description;

    private ErrorDescription(int code, String description) {
      this.code = code;
      this.description = description;
    }


    public static String getMessage(int errorCode) {
      ErrorDescription code = mapErrorCode.get(errorCode);
      if (code != null) {
        return code.description;
      } else {
        return "Not Found ErrorCode=" + errorCode;
      }
    }

    @Override
    public String toString() {
      return String.format("[%10d %s]", code, description);
    }

  }

}

