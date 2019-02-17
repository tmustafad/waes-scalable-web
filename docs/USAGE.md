# Usage

## Project Info

It was a great pleasure for me to develop this microservice. Here I provide several ways to test it.

### Prerequisites

Depending on which way you are using to test the microservice, some dependencies should be installed.

* [cURL](https://curl.haxx.se/)
* [Git](https://git-scm.com/)
* [Docker](https://www.docker.com/)
* [Postman](https://www.getpostman.com/)
* [Java 1.8](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html)
* [Maven](https://maven.apache.org/install.html)
 
### Server up and running

Independent of the way you choose to deploy and test the service (Docker, Maven Spring Boot plugin or packaged Java application), the final result must be like is shown at the image bellow.

![Service up via docker image](docker_up.png)

For the Heroku application, the following links are available:

* [API Swagger documentation](https://waes-scalable-web.herokuapp.com/swagger-ui.html)
* [Spring Boot Actuator - Health](https://waes-scalable-web.herokuapp.com/actuator/health)
* [Spring Boot Actuator - Info](https://waes-scalable-web.herokuapp.com/actuator/info)

For the other deploy methods:

* [API Swagger documentation](http://localhost:8080/swagger-ui.html)
* [Spring Boot Actuator - Health](http://localhost:8080/actuator/health)
* [Spring Boot Actuator - Info](http://localhost:8080/actuator/info)
* [H2 - Database console](http://localhost:8080/h2-console/)


#### Heroku

Along with the source code and instructions to test the microservice, I am also providing an instance running on [Heroku](https://www.heroku.com/), at [https://waes-scalable-web.herokuapp.com/](https://waes-scalable-web.herokuapp.com/).

#### Docker image

For testing with Docker, one has to open a terminal window at the `./tests` folder, and run the script provided. The script `publish-server-mvn.sh` will perform the tests, package, generate and run the microservice docker image at the port 8080.

```bash
rsouza@VYCanisMajoris: $ ~/Projecten/waes-scalable-web/tests/publish-server-mvn.sh
```
#### Maven Plugin

For testing with Maven, one has to open a terminal window at the project root folder, and run 

```bash
rsouza@VYCanisMajoris: $ ~/Projecten/waes-scalable-web/mvn spring-boot:run
```

#### Command line Java packaged application

Test, build, package and run.

```bash
rsouza@VYCanisMajoris: $ ~/Projecten/waes-scalable-web/mvn clean install && \
					   java -jar target/waes-scalable-web-0.0.1-SNAPSHOT.jar
```

### Testing

#### Postman

For tests with POSTMAN, I provide the `WAES.postman_collection.json` file.

#### CURL Script

For tests with CURL, I provide the `./tests/run-tests-curl.sh shell` script

```bash
rsouza@VYCanisMajoris: $ ~/Projecten/waes-scalable-web/tests/run-tests-curl.sh
```
