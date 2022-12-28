vygenerování changelog-u z aktuální db ([Liquibase Generate Database ChangeLog and SQL Output commands](https://www.youtube.com/watch?v=o_7d1kh9Grc) ): **mvn liquibase:generateChangeLog -Pdev**

kontrola: **mvn liquibase:changelogSyncSQL**

zapíše zpětně všechny změny do zdrojove db: **mvn liquibase:changelogSync**

aplikuje změny v changelogu do cilove db:**mvn liquibase:update**

poznámka: je nutné aby v  databázi byly správně vygenerované tabulky "**databasechangelog**" a "**databasechangeloglock**", jinak nebudou inkrementy fungovat správně!! 

//zápis pomocí javy:
java -jar taget\poc-persistence-db.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/liq_test ----spring.datasource.username=username --spring.datasource.password=password --spring.liquibase.change-log = classpath:changelog-postgresql/changelog-master.yaml

[How to insert json data into postgres database table
](https://stackoverflow.com/questions/68405302/how-to-insert-json-data-into-postgres-database-table)

[Using Liquibase with Spring Boot
](https://docs.liquibase.com/tools-integrations/springboot/springboot.html)

[Spring Boot Liquibase Up and running](https://www.youtube.com/watch?v=lf6Mxb9rVng)

[Use Liquibase to Safely Evolve](https://www.baeldung.com/liquibase-refactor-schema-of-java-app)

[Liquibase Change Types
](https://docs.liquibase.com/change-types/home.html#entities)

[Add profiles to run liquibase commands](https://github.com/mdettlaff/LiquibaseHibernateTest/pull/1/files#diff-9c5fb3d1b7e3b0f54bc5c4182965c4fe1f9023d449017cece3005d3f90e8e4d8)

[Profiles in Liquibase (my post)](https://stackoverflow.com/a/74959525/6289936)

[Create and Configure a liquibase.properties File](https://docs.liquibase.com/concepts/connections/creating-config-properties.html)





