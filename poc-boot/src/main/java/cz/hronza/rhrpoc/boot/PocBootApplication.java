package cz.hronza.rhrpoc.boot;

import cz.hronza.rhrpoc.restapi.config.PocRestApiConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = PocRestApiConfiguration.class)
public class PocBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(PocBootApplication.class, args);
    }

}
