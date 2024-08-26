#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import ${package}.domain.Item;
import ${package}.domain.ResponseBuilder;
import ${package}.exception.ApplicationException;
import ${package}.exception.ServiceErrorCode;
import ${package}.service.SampleItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/item")
public class SampleItemController {

  @Autowired
  SampleItemService itemService;

  @GetMapping("/{id}")
  public ResponseEntity<?> get(@PathVariable("id") int id,
      @RequestParam(defaultValue = "true") boolean flag) {
    if (flag) {
      return ResponseBuilder.ok(itemService.get(id));
    }
    return ResponseBuilder.notFound("The item with ID {id} could not be found.");
  }

  @PostMapping()
  public ResponseEntity<?> create(@RequestBody Item item,
      @RequestParam(defaultValue = "true") boolean flag) {
    if (flag) {
      return ResponseBuilder.created(itemService.create(item));
    }
    return ResponseBuilder.conflict(String.format("Item %d is already exist!",item.getId()));
  }

  @PutMapping("/{id}")
  public ResponseEntity<?> createOrUpdate(@RequestBody Item item,
      @RequestParam(defaultValue = "true") boolean flag) {
    if (flag) {
      return ResponseBuilder.ok(itemService.createOrUpdate(item));
    }
    return ResponseBuilder.badRequest(ServiceErrorCode.BAD_REQUEST);
  }

  @PatchMapping("{id}")
  public ResponseEntity<?> edit(@RequestBody Item item,
      @RequestParam(defaultValue = "true") boolean flag) {
    if (flag) {
      return ResponseBuilder.ok(itemService.update(item));
    }
    return ResponseBuilder.internalError(ServiceErrorCode.INTERNAL_SERVER_ERROR);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> delete(@PathVariable int id,
      @RequestParam(defaultValue = "true") boolean flag) {
    //delete exist item and return with http status 200 or 204 (no content)
    if (flag) {
      try {
        //Example of how to use ApplicationException
        throw new Exception();
      } catch (Exception ex) {
        throw new ApplicationException(ServiceErrorCode.INTERNAL_SERVER_ERROR,"Service Down");
      }

    }
    return ResponseBuilder.unauthorized(ServiceErrorCode.UNAUTHORIZED);
  }

}
