#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repository;

import ${package}.domain.Item;

public interface ItemRepository {

  Item get(int id);

  boolean create(Item item);

  boolean update(Item item);

  boolean delete(int id);

  boolean createOrUpdate(Item item);

}
