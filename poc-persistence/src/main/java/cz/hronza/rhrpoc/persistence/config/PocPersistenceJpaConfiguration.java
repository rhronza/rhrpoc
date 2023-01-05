package cz.hronza.rhrpoc.persistence.config;

import cz.hronza.rhrpoc.persistence.repository.PocPersistenceRepositoryPackage;
import cz.hronza.rhrpoc.poc_persistence_entity.PocPersistanceDomainPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.Optional;

import static cz.hronza.rhrpoc.persistence.config.PocPersistenceJpaConfiguration.AUDITING_DATE_TIME_PROVIDER_NAME_PROVIDER;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = AUDITING_DATE_TIME_PROVIDER_NAME_PROVIDER)
@EnableJpaRepositories(basePackageClasses = PocPersistenceRepositoryPackage.class)
@EntityScan(basePackageClasses = PocPersistanceDomainPackage.class)
public class PocPersistenceJpaConfiguration {
    public static final String AUDITING_DATE_TIME_PROVIDER_NAME_PROVIDER = "auditingDateTimeProvider";

    @Bean(name = AUDITING_DATE_TIME_PROVIDER_NAME_PROVIDER)
    public DateTimeProvider dateTimeProvider(Clock clock) {
        return () -> Optional.of(OffsetDateTime.now(clock));
    }


}
