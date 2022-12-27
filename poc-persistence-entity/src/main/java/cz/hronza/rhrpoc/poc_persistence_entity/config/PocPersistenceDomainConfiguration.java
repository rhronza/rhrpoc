package cz.hronza.rhrpoc.poc_persistence_entity.config;


import cz.hronza.rhrpoc.poc_persistence_entity.entity.PocPersistanceDomainPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackageClasses = PocPersistanceDomainPackage.class)
public class PocPersistenceDomainConfiguration {
}
