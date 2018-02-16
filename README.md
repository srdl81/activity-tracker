# ACTIVITY TRACKER API #
The application provides an http api for tracking activities of platsbanken.

## Prerequisites ##
- Java 8
- Gradle

## Build and run tests ##
```
gradle clean build
```

## Running the application locally ##
You can compile and start the application in debug mode with this command

```
./gradlew bootRun
```
Then you will find the api at:
http://localhost:8080/activity-tracker/swagger-ui.html


## neo4j console ##
```
http://localhost:7474/browser/
```

## neo4j log ##
```
tail -f /usr/local/Cellar/neo4j/3.3.0/libexec/logs/neo4j.log
```

## neo4j delete local db ##
```
rm -rf /usr/local/Cellar/neo4j/3.3.0/libexec/data/*
```

## IntelliJ ##
set environment variable 'NEO4J_URL' with password 'http://neo4j:lokum@localhost:7474'

## Lombok ##
Project Lombok is used to reduce boiler plate Java code.
To use this with Idea, install the Lombok Plugin (available from the plugin repository), and enable Enable Annotation Processing (Preferences -> Build, Execution, Deployment -> Compiler -> Annotation Processors).