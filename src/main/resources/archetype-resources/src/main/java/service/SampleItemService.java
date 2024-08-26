#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.domain.Item;
import ${package}.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleItemService {

  @Autowired
  ItemRepository repository;

  public Item get(int id){
    return repository.get(id);
  }

  public boolean create(Item item){
    return repository.create(item);
  }

  public boolean update(Item item){
    return repository.update(item);
  }

  public boolean createOrUpdate(Item item){
    return repository.createOrUpdate(item);
  }

  public boolean delete(int id){
    return repository.delete(id);
  }
}
