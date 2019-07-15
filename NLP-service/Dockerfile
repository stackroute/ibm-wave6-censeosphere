FROM openjdk:11

ADD ./target/NLP-service-0.0.1-SNAPSHOT.jar /rating/src/NLP-service-0.0.1-SNAPSHOT.jar

WORKDIR /rating/src

ENTRYPOINT ["java","-jar", "NLP-service-0.0.1-SNAPSHOT.jar"]