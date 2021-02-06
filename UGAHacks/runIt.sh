#! /bin/bash -ex

mvn clean
mvn compile
mvn -e -Dprism.order=sw exec:java -Dexec.mainClass="hackathon.covid.CovidDriver"
