#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service;

import ${package}.domain.Item;

public interface ItemService {

  Item get(int id);

  boolean create(Item item);

  boolean update(Item item);

  boolean createOrUpdate(Item item);

  boolean delete(int id);

}
