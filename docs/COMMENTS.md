# Comments

## Architecture

I did not try (and I did not have time) to invent anything fancy. I chose to use a vanilla implementation on my microservice, using the mainstream architecture found in countless books. So I structured my project as a stateless REST server, with the following structure:

* Rest controllers: microservice interface to the world. Responsible to process all system requests. 
* Services: Business layers. Responsible to hold the business rules.
* Repositories: JPA interfaces responsible for the persistence.
* Model: JPA (and non JPA) entities that represent data in the microservice.

And several auxiliary classes, as:

* DTO: Almost POJO's responsible for the data delivery from the REST endpoints.
* Enums: Enumerations with constants that make sense being grouped in a context (Difference result, difference types).
* Exceptions: Some exception (not all) that could happen inside the business logic.
* Util/Json: Classes responsible for the JSON difference.

## Technical Stack
- Spring Boot 2
- Java 8
- H2 (in-memory database)
- Maven 3
- Swagger 2

## System Requirements
- JDK 8+
- Maven 3.3+

## On the choice of the persistence layer 

The preferential way to implement the persistence layer (in a production scenario) would be a more robust database(MongoDB, MySQL, etc). The H2 database were chosen because of its simplicity (no need for installation or use of a SaaS subscription/Docker compose solution for the demonstration). In my opinion the H2 database fits our purpose.

## On the choice of the Difference response

I felt that, instead of saying "the data has the same size and are both equal" or "the data is different", it would be much nicer to say "The left content has X just like the right content, but for the left the value is A and for the right the value is B", or "the left content has X and Y, but the right has only Z", so I kind of implemented in this way. :-) 

## Spring Actuator

For security reasons, only the end points `health` and `info` are available. On a production environment these conditions can be relaxed, if using with Spring Boot Dataflow Server/Spring Boot Admin.

## Improvements

Lets start by the more obvious improvements:

* A DELETE endpoint can be provided to purge transactions.
* Use of a real-world database.

Now some not-so obvious improvements:

* Thinking on the horizontal scalability of the service, we know there is an amazing tool under the Spring Framework called Spring Cloud that provide some cloud design-patterns implementations which enables the application to scale seamlessly. So, for a robust and scalable implementation one can use Spring Eureka(Service discovery), Configuration Server, Zuul Server(API gateway), Spring Boot Admin and so on.
* I am aware that some code I implemented on the project is old school. A better use of streams and lambdas, to name a few Java 8 features, would be nice to provide a better resource allocation by the JVM.   

## What I did not have time to implement

* Better logs
* Services unit tests

## And...

I would like to thanks WAES for the opportunity. :-)