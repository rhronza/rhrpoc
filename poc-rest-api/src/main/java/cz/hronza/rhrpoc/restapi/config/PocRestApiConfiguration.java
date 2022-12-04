package cz.hronza.rhrpoc.restapi.config;

import cz.hronza.rhrpoc.business_logic.config.PocBusinessLogicConfiguration;
import cz.hronza.rhrpoc.core.api.configuration.RhrpocCoreApiConfiguration;
import cz.hronza.rhrpoc.restapi.PocRestApiPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackageClasses = PocRestApiPackage.class)
@Import(value = {PocBusinessLogicConfiguration.class, RhrpocCoreApiConfiguration.class} )
public class PocRestApiConfiguration {
    
}
