#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

public class HealthStatus {

  @JsonProperty("database")
  boolean databaseStatus;
  @JsonProperty("cache")
  boolean cacheStatus;


  public void setDatabaseStatus(boolean isHealthy) {
    this.databaseStatus = isHealthy;
  }

  public boolean getDatabaseStatus(){
    return this.databaseStatus;
  }

  public void setCacheStatus(boolean isHealthy) {
    this.cacheStatus = isHealthy;
  }
  public boolean getCacheStatus(){
    return cacheStatus;
  }

  public boolean isOk() {
    return databaseStatus && cacheStatus;
  }

}
