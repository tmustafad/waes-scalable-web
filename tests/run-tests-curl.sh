#!/bin/bash
#
#
# run-tests-curl.sh - Tests the waes-scalable-web endpoints
#
# Author: 	Rodrigo Alvares de Souza
# 			rsouza01@gmail.com
#
#
# History:
# Version 0.1: 2019/02/17 (rsouza01) - First version
#


#FUNCTIONS DEFINITIONS

print2stringslncolor () {
        echo -e "\e[0m$1\e[1;34m$2\e[0m\n"
}


print2stringslncolorERROR () {
        echo -e "\e[0m$1\e[1;91m$2\e[0m\n"
}

printlncolor () {
        echo -e "\e[1;34m$1\e[0m\n"
}

printlncolorERROR () {
        echo -e "\e[1;91m$1\e[0m\n"
}
#END FUNCTIONS DEFINITIONS

#MAIN PROGRAM

USE_MESSAGE="
Usage: $(basename "$0") [OPTION]

OPTIONS:
  -l, --local          Run tests against 'localhost'.
  -e, --external       Run tests against 'heroku'.

  -h, --help           Show this help screen and exits
  -V, --version        Show program version and exits
"

_VERSION=$(grep '^# Version ' "$0" | tail -1 | cut -d : -f 1 | tr -d \#)

_API_ADDRESS=""

#Command line arguments
while test -n "$1"
do
        case "$1" in

		-l | --local) 
                        shift
                        _API_ADDRESS="localhost:8080" 
                ;;

		-e | --external) 
                        shift
                        _API_ADDRESS="https://waes-scalable-web.herokuapp.com" 
                ;;

		-h | --help)
			echo "$USE_MESSAGE"
			exit 0
		;;

		-V | --version)
			echo -n $(basename "$0")
                        echo " ${_VERSION}"
			exit 0
		;;

		*)
			echo Invalid option: $1
			exit 1
		;;
	esac

	shift
done

if [ -z "$_API_ADDRESS" ]
then
	_API_ADDRESS="localhost:8080"
fi

clear

printlncolor "\n\n__________________________________________________________________________________________________________"
printlncolor "---------------------  WAES SCALABLE WEB Command line tester  ${_VERSION} -------------------------------"
printlncolor "__________________________________________________________________________________________________________"

printlncolor "Tests will be performed against '${_API_ADDRESS}'."

printlncolor "Checking for cURL installation."

if ! [ -x "$(command -v curl)" ]; then
  printlncolorERROR 'Error: cUrl is not installed. Please install and run this script again.' >&2
  exit 1
else
  printlncolor 'cURL installed..'
fi

printlncolor "Starting the tests."


_TRANSACTION_ID=1
printlncolorERROR '----------------------------------------------------------------------------------------------------------'
printlncolorERROR "Valid input - EQUAL CONTENTS - Transaction Id = ${_TRANSACTION_ID}"
printlncolorERROR '----------------------------------------------------------------------------------------------------------'

printlncolor '----------------------------------------------------------------------------------------------------------'
printlncolor "Valid input - left panel - Transaction Id = ${_TRANSACTION_ID}"
printlncolor "Endpoint: ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}/left"
printlncolor "Expected HTTP STATUS: 201 (CREATED)"
printlncolor '----------------------------------------------------------------------------------------------------------'
curl -i --data \
	'{"base64Content":"eyAibmFtZSI6IkpvaG4iLCAiYWdlIjozMCwgImNhcnMiOiB7ICJjYXIxIjoiRm9yZCIsICJjYXIyIjoiQk1XIiwgImNhcjMiOiJGaWF0IiB9IH0=" }' \
	-H "Content-Type: application/json" -X POST ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}/left
echo


printlncolor '----------------------------------------------------------------------------------------------------------'
printlncolor "Valid input - right panel - Transaction Id = ${_TRANSACTION_ID}"
printlncolor "Endpoint: ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}/right"
printlncolor "Expected HTTP STATUS: 201 (CREATED)"
printlncolor '----------------------------------------------------------------------------------------------------------'
curl -i --data \
	'{"base64Content":"eyAibmFtZSI6IkpvaG4iLCAiYWdlIjozMCwgImNhcnMiOiB7ICJjYXIxIjoiRm9yZCIsICJjYXIyIjoiQk1XIiwgImNhcjMiOiJGaWF0IiB9IH0=" }' \
	-H "Content-Type: application/json" -X POST ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}/right
echo


printlncolor '----------------------------------------------------------------------------------------------------------'
printlncolor "Valid input - Difference - Transaction Id = ${_TRANSACTION_ID}"
printlncolor "Endpoint: ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}"
printlncolor "Expected result: EQUAL_CONTENTS"
printlncolor "Expected HTTP STATUS: 200 (OK)"
printlncolor '----------------------------------------------------------------------------------------------------------'
curl -i -H "Content-Type: application/json" -X GET ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}
	
echo
echo

_TRANSACTION_ID=2
printlncolorERROR '----------------------------------------------------------------------------------------------------------'
printlncolorERROR "Invalid input - TRYING TO GET DIFFERENCE WITH ONLY ONE PANEL LOADED - Transaction Id = ${_TRANSACTION_ID}"
printlncolorERROR '----------------------------------------------------------------------------------------------------------'

printlncolor '----------------------------------------------------------------------------------------------------------'
printlncolor "Valid input - right panel - Transaction Id = ${_TRANSACTION_ID}"
printlncolor "Endpoint: ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}/right"
printlncolor "Expected HTTP STATUS: 201 (CREATED)"
printlncolor '----------------------------------------------------------------------------------------------------------'
curl -i --data \
	'{"base64Content":"eyAibmFtZSI6IkpvaG4iLCAiYWdlIjozMCwgImNhcnMiOiB7ICJjYXIxIjoiRm9yZCIsICJjYXIyIjoiQk1XIiwgImNhcjMiOiJGaWF0IiB9IH0=" }' \
	-H "Content-Type: application/json" -X POST ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}/right
echo


printlncolor '----------------------------------------------------------------------------------------------------------'
printlncolor "Invalid input - Only one panel loaded - Transaction Id = ${_TRANSACTION_ID}"
printlncolor "Endpoint: ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}"
printlncolor "Expected message: 'There are not enough content to compare'"
printlncolor "Expected HTTP STATUS: 422 (UNPROCESSABLE ENTITY)"
printlncolor '----------------------------------------------------------------------------------------------------------'
curl -i -H "Content-Type: application/json" -X GET ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}
	
echo


_TRANSACTION_ID=3
printlncolorERROR '----------------------------------------------------------------------------------------------------------'
printlncolorERROR "Valid input - DIFFERENT CONTENTS - Transaction Id = ${_TRANSACTION_ID}"
printlncolorERROR '----------------------------------------------------------------------------------------------------------'

printlncolor '----------------------------------------------------------------------------------------------------------'
printlncolor "Valid input - left panel - Transaction Id = ${_TRANSACTION_ID}"
printlncolor "Endpoint: ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}/left"
printlncolor "Expected HTTP STATUS: 201 (CREATED)"
printlncolor '----------------------------------------------------------------------------------------------------------'
curl -i --data \
	'{"base64Content":"eyAibmFtZSI6IkpvaG4iLCAiYWdlIjozMCwgImNhcnMiOiB7ICJjYXIxIjoiRm9yZCIsICJjYXIyIjoiQk1XIiwgImNhcjMiOiJGaWF0IiB9IH0=" }' \
	-H "Content-Type: application/json" -X POST ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}/left
echo


printlncolor '----------------------------------------------------------------------------------------------------------'
printlncolor "Valid input - right panel - Transaction Id = ${_TRANSACTION_ID}"
printlncolor "Endpoint: ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}/right"
printlncolor "Expected HTTP STATUS: 201 (CREATED)"
printlncolor '----------------------------------------------------------------------------------------------------------'
curl -i --data \
	'{"base64Content":"eyAibmFtZSI6IkFuYSIsICJhZ2UiOjQwLCAiY2FycyI6IHsgImNhcjEiOiJGb3JkIiwgImNhcjIiOiJCTVciIH0gfQ==" }' \
	-H "Content-Type: application/json" -X POST ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}/right
echo


printlncolor '----------------------------------------------------------------------------------------------------------'
printlncolor "Valid input - Difference - Transaction Id = ${_TRANSACTION_ID}"
printlncolor "Endpoint: ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}"
printlncolor "Expected result: DIFFERENT_SIZES_CONTENTS"
printlncolor "Expected HTTP STATUS: 200 (OK)"
printlncolor '----------------------------------------------------------------------------------------------------------'
curl -i -H "Content-Type: application/json" -X GET ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}
	
echo
echo

_TRANSACTION_ID=4
printlncolorERROR '----------------------------------------------------------------------------------------------------------'
printlncolorERROR "Invalid input - Upload empty content - Transaction Id = ${_TRANSACTION_ID}"
printlncolorERROR '----------------------------------------------------------------------------------------------------------'

printlncolor '----------------------------------------------------------------------------------------------------------'
printlncolor "Invalid input - Left panel - Transaction Id = ${_TRANSACTION_ID}"
printlncolor "Endpoint: ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}/left"
printlncolor "Expected HTTP STATUS: 400 (BAD REQUEST)"
printlncolor '----------------------------------------------------------------------------------------------------------'
curl -i --data '{ }' -H "Content-Type: application/json" -X POST ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}/left
echo

printlncolor '----------------------------------------------------------------------------------------------------------'
printlncolor "Invalid input - Right panel - Transaction Id = ${_TRANSACTION_ID}"
printlncolor "Endpoint: ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}/right"
printlncolor "Expected HTTP STATUS: 400 (BAD REQUEST)"
printlncolor '----------------------------------------------------------------------------------------------------------'
curl -i --data '{ }' -H "Content-Type: application/json" -X POST ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}/right
echo

echo
echo

_TRANSACTION_ID=5
printlncolorERROR '----------------------------------------------------------------------------------------------------------'
printlncolorERROR "Invalid transaction Id - No loadded panel - Transaction Id = ${_TRANSACTION_ID}"
printlncolorERROR '----------------------------------------------------------------------------------------------------------'

printlncolor '----------------------------------------------------------------------------------------------------------'
printlncolor "Invalid transaction - Difference - Transaction Id = ${_TRANSACTION_ID}"
printlncolor "Endpoint: ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}"
printlncolor "Expected message: 'No transaction found for the transactionId provided'"
printlncolor "Expected HTTP STATUS: 404 (NOT FOUND)"
printlncolor '----------------------------------------------------------------------------------------------------------'
curl -i -H "Content-Type: application/json" -X GET ${_API_ADDRESS}/v1/diff/${_TRANSACTION_ID}


echo
echo
printlncolor "Done."



exit 0



clear

