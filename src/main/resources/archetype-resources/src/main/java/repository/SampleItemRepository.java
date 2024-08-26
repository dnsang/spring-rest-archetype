#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository;

import ${package}.domain.Item;
import java.time.LocalDateTime;
import org.springframework.stereotype.Repository;

@Repository
public class SampleItemRepository implements ItemRepository{

  public Item get(int id) {
    return Item.builder().id(1).name("test").
        createdDate(LocalDateTime.now()).build();
  }

  public boolean create(Item item) {
    return true;
  }

  public boolean update(Item item) {
    return true;
  }

  public boolean delete(int id) {
    return true;
  }

  public boolean createOrUpdate(Item item) {
    return true;
  }
}
