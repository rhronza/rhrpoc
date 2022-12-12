package cz.hronza.rhrpoc.business_logic.config;

import cz.hronza.rhpoc.easy_be.config.PocEasyBeConfiguration;
import cz.hronza.rhrpoc.business_logic.PocBusinessLogicPackage;
import cz.hronza.rhrpoc.persistence.config.PocPersistenceJpaConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.Clock;

@Configuration
@ComponentScan(basePackageClasses = PocBusinessLogicPackage.class)
@Import({PocPersistenceJpaConfiguration.class,
        PocEasyBeConfiguration.class,
})
public class PocBusinessLogicConfiguration {
    @Bean
    public Clock systemDefaultZoneClock() {
        return Clock.systemDefaultZone();
    }


}
