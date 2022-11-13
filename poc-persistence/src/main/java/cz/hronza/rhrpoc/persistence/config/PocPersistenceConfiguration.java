package cz.hronza.rhrpoc.persistence.config;

import cz.hronza.rhrpoc.persistence.PocPersistencePackage;
import cz.hronza.rhrpoc.poc_persistence_db.PocPersistenceDbPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackageClasses = PocPersistencePackage.class)
@Import(value = PocPersistenceDbPackage.class)
public class PocPersistenceConfiguration {
}
