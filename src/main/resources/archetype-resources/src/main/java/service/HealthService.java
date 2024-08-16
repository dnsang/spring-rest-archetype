#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import org.springframework.stereotype.Service;
import ${package}.domain.HealthStatus;

@Service
public class HealthService {

  public HealthStatus checkHealth(){
    HealthStatus healthStatus = new HealthStatus();
    healthStatus.setCacheStatus(true);
    healthStatus.setDatabaseStatus(true);
    return healthStatus;
  }
}
