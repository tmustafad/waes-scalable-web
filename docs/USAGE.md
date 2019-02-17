# Usage

## Project Info

It was a great pleasure for me to develop this microservice. Here I provide saveral ways to test it.



[Swagger documentation](http://localhost:8080/swagger-ui.html)

[H2 Console](http://localhost:8080/h2-console)

## Usage

In order to run the tests, the user have to use either Postman or CURL instaled.

### Postman

For tests with POSTMAN, I provide the WAES.postman_collection.json file.

### CURL

For tests with CURL, I provide the run-tests-curl.sh shell script.

#### Via Heroku

[Swagger documentation](https://waes-scalable-web.herokuapp.com/swagger-ui.html)

[Actuator - Health](https://waes-scalable-web.herokuapp.com/actuator/health)
[Actuator - Info](https://waes-scalable-web.herokuapp.com/actuator/info)

#### Via command line

mvn spring-boot:run