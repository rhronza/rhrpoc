package cz.hronza.restapi.config;

import cz.hronza.business_logic.config.PocBusinessLogicConfiguration;
import cz.hronza.restapi.PocRestApiPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackageClasses = PocRestApiPackage.class)
@Import(value = PocBusinessLogicConfiguration.class)
public class PocRestApiConfiguration {
    
}
