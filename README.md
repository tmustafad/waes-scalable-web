# WAES Assignment Scalable Web [![Build Status](https://travis-ci.org/rsouza01/waes-scalable-web.svg?branch=master)](https://travis-ci.org/rsouza01/waes-scalable-web) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## GOAL

The goal of this assignment is to show your coding skills and what you value in software engineering. We value new ideas so next to the original requirement feel free to improve/add/extend.
We evaluate the assignment depending on your role (Developer/Tester) and your level of seniority


## The assignment

- Provide 2 http endpoints that accepts JSON base64 encoded binary data on both endpoints
	- <host>/v1/diff/<ID>/left and <host>/v1/diff/<ID>/right
- The provided data needs to be diff-ed and the results shall be available on a third end point
	- <host>/v1/diff/<ID>
- The results shall provide the following info in JSON format
	- If equal return that
	- If not of equal size just return that
	- If of same size provide insight in where the diffs are, actual diffs are not needed (So mainly offsets + length in the data)
- Make assumptions in the implementation explicit, choices are good but need to be communicated


## Must haves
- Solution written in Java
- Internal logic shall be under unit test
- Functionality shall be under integration test
- Documentation in code
- Clear and to the point readme on usage

## Nice to haves
- Suggestions for improvement

Please	upload	the	assignment	on	your	personal	GitHub	account	once	finished,	and	send	the	link	to	the	
responsible	Tech	Sourcer before	deadline.


## Project Info

Swagger documentation: http://localhost:8080/swagger-ui.html
H2 Console: http://localhost:8080/h2-console


## Usage

In order to run the tests, the user have to use either Postman or CURL instaled.

### Postman

For tests with POSTMAN, I provide the WAES.postman_collection.json file.

### CURL

For tests with CURL, I provide the run-tests-curl.sh shell script.



#### Via Heroku

Swagger-UI
https://waes-scalable-web.herokuapp.com/swagger-ui.html


Actuator
https://waes-scalable-web.herokuapp.com/actuator/health
https://waes-scalable-web.herokuapp.com/actuator/info

#### Via command line

mvn spring-boot:run


