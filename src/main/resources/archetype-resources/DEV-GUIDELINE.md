# Development Guideline
## Overview
This guideline provides a step-by-step guide on how to use the new archetype for creating a Spring Boot project. 
It covers key aspects such as creating controllers, handling exceptions, and following best practices.

## Getting Started
### 1. Creating a New Project

To create a new project using the archetype, run the following Maven command:

```shell
mvn archetype:generate -B -DarchetypeGroupId=dev.sang.archetype 	\
-DarchetypeArtifactId=spring-rest-archetype 		\
-DarchetypeVersion=0.1.1  \
-DgroupId=com.example 		\
-DartifactId=project 		  \
-Dpackage=com.example.project	\
-DarchetypeCatalog=remote
```

Replace groupId, artifactId and package with your desired name.

### 2. Project Structure

The generated project will follow the standard 3-layer architecture structure:

```bash
project/
├── cmd/
├── conf/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/project/
│   │   │       ├── config/
│   │   │       ├── controller/
│   │   │           └── SampleItemController.java
│   │   │       ├── service/
│   │   │       ├── repository/
│   │   │       └── exception/
│   │   │           └── GlobalExceptionHandler.java
│   │   │       └── domain/
│   │   │       └── JettyWebserver.java
│   │   │       └── Server.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
│   │   │   └── com/example/project/
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── repository/

```

### 3.Creating a Controller
#### 3.1. Defining a REST Controller
Create a new controller in the controller package:

```java
package com.example.project.controller;

@RestController
@RequestMapping("/api/v1/item")
public class SampleItemController {

   @Autowired
   SampleItemService itemService;

   @GetMapping("/{id}")
   public ResponseEntity<?> get(@PathVariable("id") int id) {      
        return ResponseBuilder.ok(itemService.get(id));
   }
```

### 4. Handling Exceptions

To handle exceptions globally, take a look at GlobalExceptionHandler class:

```java
package com.example.myapp.exception;

@ControllerAdvice
public final class GlobalExceptionHandler {

 @ExceptionHandler(Exception.class)
 public ResponseEntity<?> handleException(Exception e){
   return ResponseBuilder.internalError(e.getMessage());
 }
 
 @ExceptionHandler(ApplicationException.class)
 public ResponseEntity<?> handleApplicationException(ApplicationException ex){
  return ResponseBuilder.fail(HttpStatus.INTERNAL_SERVER_ERROR,ex.getErrorCode(), ex.getMessage());
 }
}
```

# Exception Handling Guidelines

**There are three ways to handle your custom exceptions:**

1. **Create a Custom Exception Handler Class**  
   Similar to the `GlobalExceptionHandler` class, you can create your own exception handler class to manage specific exceptions.

2. **Reuse and Throw `ApplicationException`**  
   In your code, you can reuse the `ApplicationException` class and throw new instances of it as needed.

3. **Create a Custom Exception Class**  
   Extend the `ApplicationException` class to create your custom exception:

```java
package com.example.project.exception;

public class ItemNotFoundException extends ApplicationException {
    public ItemNotFoundException(Long id) {
        super(ServiceErrorCode.INTERNAL_SERVER_ERROR, "Item with ID " + id + " not found");
    }
}
```

**Note:** Consider using `ApplicationException` with a detailed message for better clarity and consistency in error handling.

### 5. API Response Format 

[Response Format](RESPONSE-FORMAT.md)
