package cz.hronza.business_logic.config;

import cz.hronza.business_logic.PocBusinessLogicPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = PocBusinessLogicPackage.class )
public class PocBusinessLogicConfiguration {

}
