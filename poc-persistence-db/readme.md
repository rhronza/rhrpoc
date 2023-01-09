Vygenerování changelog-u z aktuální db ([Liquibase Generate Database ChangeLog and SQL Output commands](https://www.youtube.com/watch?v=o_7d1kh9Grc) ): **mvn liquibase:generateChangeLog -Pdev**

Kontrola: **mvn liquibase:changelogSyncSQL**

Zapíše zpětně všechny změny do zdrojove db: **mvn liquibase:changelogSync**

Aplikuje změny v changelogu do cilove db:**mvn liquibase:update**

*Poznámka*: je nutné aby v  databázi byly správně vygenerované tabulky "**databasechangelog**" a "**databasechangeloglock**", jinak nebudou inkrementy fungovat správně!! 

*Zápis pomocí javy*:
java -jar taget\poc-persistence-db.jar --spring.datasource.url=jdbc:postgresql://localhost:5432/liq_test ----spring.datasource.username=username --spring.datasource.password=password --spring.liquibase.change-log = classpath:changelog-postgresql/changelog-master.yaml







