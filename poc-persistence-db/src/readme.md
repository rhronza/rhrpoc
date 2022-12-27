vygenerování changelog-u z aktuální db ([Liquibase Generate Database ChangeLog and SQL Output commands](https://www.youtube.com/watch?v=o_7d1kh9Grc) ): **mvn liquibase:generateChangeLog -Dliquibase.profile=local-dev**

kontrola: **mvn liquibase:changelogSyncSQL**

zapíše zpětně všechny změny do zdrojove db: **mvn liquibase:changelogSync**

aplikuje změny v changelogu do cilove db:**mvn liquibase:update**

poznámka: je nutné aby v  databázi byly správně vygenerované tabulky "**databasechangelog**" a "**databasechangeloglock**", jinak nebudou inkrementy fungovat správně!! 

//zápis pomocí javy:
java -jar taget\poc-persistence-db.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/liq_test ----spring.datasource.username=username --spring.datasource.password=password --spring.liquibase.change-log = classpath:changelog-postgresql/changelog-master.yaml



https://docs.liquibase.com/tools-integrations/springboot/springboot.html

https://www.youtube.com/watch?v=lf6Mxb9rVng&t

https://www.baeldung.com/liquibase-refactor-schema-of-java-app


https://docs.liquibase.com/change-types/home.html#entities


