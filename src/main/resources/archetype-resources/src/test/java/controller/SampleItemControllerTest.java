#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ${package}.domain.APIResponse;
import ${package}.domain.Item;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SampleItemControllerTest {

  @LocalServerPort
  private int port;
  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void testGetItemShouldReturnOk200() {
    String url = String.format("http://localhost:%d/api/v1/item/1", port);
      ResponseEntity<?> response = restTemplate.getForEntity(url, APIResponse.class);
      assertTrue(response.getStatusCode().is2xxSuccessful());
      assertEquals(200, response.getStatusCode().value());
  }

  @Test
  public void testGetItemShouldReturnNotFound404() {
    String url = String.format("http://localhost:%d/api/v1/item/1?flag=false", port);
    ResponseEntity<?> response = restTemplate.getForEntity(url, APIResponse.class);
    assertTrue(response.getStatusCode().isError());
    assertEquals(404, response.getStatusCode().value());
  }

  @Test
  public void testCreateNewItemShouldReturnCreated201() {
    String url = String.format("http://localhost:%d/api/v1/item", port);
    Item item = Item.builder().id(1).name("test").createdDate(LocalDateTime.now()).build();
    ResponseEntity<?> response = restTemplate.postForEntity(url, item, APIResponse.class);
    assertEquals(201, response.getStatusCode().value());
  }

  @Test
  public void testCreateNewItemShouldReturnConflict409() {
    String url = String.format("http://localhost:%d/api/v1/item?flag=false", port);
    Item item = Item.builder().id(1).name("test").createdDate(LocalDateTime.now()).build();
    ResponseEntity<?> response = restTemplate.postForEntity(url, item, APIResponse.class);
    assertTrue(response.getStatusCode().isError());
    assertEquals(409, response.getStatusCode().value());
  }

  @Test
  public void testCreateOrUpdateItemShouldReturnOk200() {
    String url = String.format("http://localhost:%d/api/v1/item/1", port);
    Item item = Item.builder().id(1).name("test").createdDate(LocalDateTime.now()).build();
    HttpEntity<Item> items = new HttpEntity<>(item);
    ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.PUT, items,
                                                       APIResponse.class);
    assertEquals(200, response.getStatusCode().value());
  }

  @Test
  public void testCreateOrUpdateItemShouldReturnBadRequest400() {
    String url = String.format("http://localhost:%d/api/v1/item/1?flag=false", port);
    Item item = Item.builder().id(1).name("test").createdDate(LocalDateTime.now()).build();
    HttpEntity<Item> items = new HttpEntity<>(item);
    ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.PUT, items,
                                                       APIResponse.class);
    assertEquals(400, response.getStatusCode().value());
  }

  @Test
  public void testEditItemShouldReturnOk200() {

    String url = String.format("http://localhost:%d/api/v1/item/1", port);
    Item item = Item.builder()
        .id(1)
        .name("test")
        .createdDate(LocalDateTime.now())
        .build();
    HttpEntity<Item> items = new HttpEntity<>(item);
    ResponseEntity<?> response = restTemplate.exchange(url,
                                                       HttpMethod.PATCH,
                                                       items,
                                                       APIResponse.class);
    assertEquals(200, response.getStatusCode().value());
  }

  @Test
  public void testEditItemShouldReturnInternalServerError500() {
    String url = String.format("http://localhost:%d/api/v1/item/1?flag=false", port);
    Item item = Item.builder().id(1).name("test").createdDate(LocalDateTime.now()).build();
    HttpEntity<Item> items = new HttpEntity<>(item);
    ResponseEntity<?> response = restTemplate.exchange(url,
                                                       HttpMethod.PATCH,
                                                       items,
                                                       APIResponse.class);
    assertEquals(500, response.getStatusCode().value());

  }
}
