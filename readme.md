# Simple Calculator

## Features
- Perform simple calulators
- Store calculation history in H2 database

## Supported versions
- Java 11
- Spring Boot 2.6.1

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `nl.quintor.simplecalculator.SimpleCalculatorApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Note on database
The application currently auto generates tables and entities.
For further development, version control like Liquibase is recommended.
