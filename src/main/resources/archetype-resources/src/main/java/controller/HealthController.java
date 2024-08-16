#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;


import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ${package}.domain.HealthStatus;
import ${package}.service.HealthService;

@RestController
public class HealthController {

  private Logger logger = LoggerFactory.getLogger(HealthController.class);
  @Autowired
  HealthService healthService;

  @GetMapping("/api/v1/health")
  public Map<String, String> checkHealth() {
      HealthStatus status = healthService.checkHealth();
      return Map.of(
          "status", String.valueOf(status.isOk()),
          "database", String.valueOf(status.getCacheStatus()),
          "cache", String.valueOf(status.getDatabaseStatus())
      );
  }


  @GetMapping("/api/v2/health")
  public Map<String, String> checkHealth2() throws Exception {
      throw new UnsupportedOperationException("not supported yet");
  }

}
