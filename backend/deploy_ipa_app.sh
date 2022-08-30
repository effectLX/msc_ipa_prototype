#!/bin/bash

export NAME="ipa-app"

source ./util.sh

mvn clean deploy -DskipTests=true -Dimage="${DOCKER_IMAGE_URL}" -Djib.to.tags="${DOCKER_IMAGE_TAG}",latest