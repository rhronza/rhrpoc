package cz.hronza.business_logic.config;

import cz.hronza.business_logic.PocBusinessLogicPackage;
import cz.hronza.persistence.config.PocPersistenceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackageClasses = PocBusinessLogicPackage.class )
@Import(value = PocPersistenceConfiguration.class)
public class PocBusinessLogicConfiguration {

}
