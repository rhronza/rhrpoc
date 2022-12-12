package cz.hronza.rhrpoc.poc_persistence_domain.config;


import cz.hronza.rhrpoc.poc_persistence_domain.entity.PocPersistanceDomainPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackageClasses = PocPersistanceDomainPackage.class)
public class PocPersistenceDomainConfiguration {
}
