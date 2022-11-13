package cz.hronza.rhrpoc.business_logic.config;

import cz.hronza.rhrpoc.business_logic.PocBusinessLogicPackage;
import cz.hronza.rhrpoc.persistence.config.PocPersistenceConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackageClasses = PocBusinessLogicPackage.class )
@Import(value = PocPersistenceConfiguration.class)
public class PocBusinessLogicConfiguration {

}
