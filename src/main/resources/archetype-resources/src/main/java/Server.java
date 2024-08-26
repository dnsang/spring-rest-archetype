#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import java.util.Arrays;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;


@SpringBootApplication
public class Server {

  public static void main(String[] args) {
    SpringApplication.run(Server.class, args);
  }

  @Bean
  ApplicationRunner applicationRunner(Environment environment) {
    return args -> {
      LoggerFactory.getLogger("console")
          .info("Active Profile: " + Arrays.toString(environment.getActiveProfiles()));
    };
  }
}
