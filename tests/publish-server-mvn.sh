#!/usr/bin/env bash
#
# publish-server-mvn.sh - Build and push the docker image to dockerhub.
#
# Author: 	Rodrigo Alvares de Souza (rsouza01@gmail.com)
#
#
# IMPORTANT:
# Do not forget to enable docker
# Either by 
# *	adding your user to docker group or 
# * by running 'sudo chmod 777 /var/run/docker.sock'
#
# History:
# Version 0.1: 2019/01/03 (rsouza) - First version
#

# Image settings
ORG_NAME=rsouza01
SERVER_NAME=waes-scalable-web
SERVER_PORT=8080
IMG_TAG=latest

# Environment settings
PORT_CONTAINER=8080
PORT_HOST=8080

_HEADER="[INFO] ========================================================================"
_USE_MESSAGE="
Usage: $(basename "$0") [OPTIONS]
OPTIONS:
  -h, --help            Show this help screen and exits.
  -V, --version         Show program version and exits.
"
_VERSION=$(grep '^# Version ' "$0" | tail -1 | cut -d : -f 1 | tr -d \#)

clear
echo $_HEADER
echo "[INFO]" $(basename "$0") " ${_VERSION}"
echo $_HEADER

#Command line arguments
case $1 in

		-h | --help)
			echo "$_USE_MESSAGE"
			exit 0
		;;

		-V | --version)
			echo -n $(basename "$0")
            echo " ${_VERSION}"
			exit 0
		;;
esac


MVN='mvn'
#MAVEN_FLAGS='-f ../pom.xml -DskipTests'
MAVEN_FLAGS='-f ../pom.xml'
MAVEN="$MVN $MAVEN_FLAGS"

echo "[INFO] ------------------------------------------------------------------------"
echo "[INFO] Running maven for $SERVER_NAME."
echo "[INFO] ------------------------------------------------------------------------"

$MAVEN clean package

if [ $? -ne 0 ]; then
	echo "[ERROR] ------------------------------------------------------------------------"
	echo "[ERROR] Error running '$MAVEN clean package'."
	echo "[ERROR] ------------------------------------------------------------------------"
	exit -1
fi


echo "[INFO] ------------------------------------------------------------------------"
echo "[INFO] Dockerizing $SERVER_NAME:$IMG_TAG."
echo "[INFO] ------------------------------------------------------------------------"

$MAVEN docker:build -e

if [ $? -ne 0 ]; then
	echo "[ERROR] ------------------------------------------------------------------------"
	echo "[ERROR] Error running '$MAVEN docker:build -e'."
	echo "[ERROR] ------------------------------------------------------------------------"
	exit -1
fi

docker run --rm --name=$SERVER_NAME --publish=$SERVER_PORT:$SERVER_PORT -it $ORG_NAME/$SERVER_NAME:latest


echo "[INFO] ------------------------------------------------------------------------"
echo "[INFO] Done!"
echo "[INFO] ------------------------------------------------------------------------"
