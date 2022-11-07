package cz.hronza.poc_persistence_db.config;


import cz.hronza.poc_persistence_db.PocPersistenceDbPackage;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = PocPersistenceDbPackage.class)
public class PocPersistenceDbConfiguration {
}
