FROM openjdk:11.0.3-jdk-slim-stretch
ADD ./target/recommendation-0.0.1-SNAPSHOT.jar  /app/demo/recommendation-0.0.1-SNAPSHOT.jar
WORKDIR   app/demo
ENTRYPOINT  ["java","-jar","recommendation-0.0.1-SNAPSHOT.jar"]