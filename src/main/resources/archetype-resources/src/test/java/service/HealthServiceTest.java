#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ${package}.domain.HealthStatus;

@SpringBootTest
public class HealthServiceTest {

  @Autowired
  HealthService healthService;

  @Test
  public void checkHealthMustReturnData(){
    HealthStatus healthStatus = healthService.checkHealth();
    assertTrue(healthStatus.isOk());
  }
}
