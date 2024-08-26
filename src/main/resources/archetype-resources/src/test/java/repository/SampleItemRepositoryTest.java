#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository;

import ${package}.domain.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SampleItemRepositoryTest {

  @Autowired
  SampleItemRepository itemRepository;

  @Test
  public void getShouldReturnItem() {
    Item item = itemRepository.get(1);
    Assertions.assertNotNull(item);
    Assertions.assertEquals(1, item.getId());
  }

}
