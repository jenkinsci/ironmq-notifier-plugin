#!/bin/bash

export MAVEN_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,address=8000,suspend=n"
mvn clean --settings ./settings.xml compile hpi:run

