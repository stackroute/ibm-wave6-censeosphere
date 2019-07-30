FROM openjdk:11.0.3-jdk-slim-stretch
ADD ./target/recommendation-service-0.0.1-SNAPSHOT.jar  /app/demo/recommendation-service-0.0.1-SNAPSHOT.jar
WORKDIR   app/demo
ENTRYPOINT  ["java","-jar","recommendation-service-0.0.1-SNAPSHOT.jar"]
