package cz.hronza.rhrpoc.persistence.config;

import cz.hronza.rhrpoc.poc_persistence_entity.config.PocPersistenceDomainConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.time.Clock;

@Configuration
@Import({PocPersistenceDomainConfiguration.class, PocPersistenceJpaConfiguration.class})
public class PocPersistenceConfiguration {
    @Bean
    public Clock systemDefaultZoneClock() {
        return Clock.systemDefaultZone();
    }
}
