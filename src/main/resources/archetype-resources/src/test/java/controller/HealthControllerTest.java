#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import ${package}.domain.HealthStatus;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HealthControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void healthCheckV1ShouldReturnOk(){
    String url = String.format("http://localhost:%d/api/v1/health",port);
    ResponseEntity<HealthStatus> response = restTemplate.getForEntity(url, HealthStatus.class);
    assertTrue(response.getStatusCode().is2xxSuccessful());
    assertTrue(response.getBody().isOk());
  }

  @Test
  public void healthCheckV2ShouldReturnError(){
    String url = String.format("http://localhost:%d/api/v2/health",port);
    ResponseEntity<HealthStatus> response = restTemplate.getForEntity(url, HealthStatus.class);
    assertTrue(response.getStatusCode().is5xxServerError());
  }
}
