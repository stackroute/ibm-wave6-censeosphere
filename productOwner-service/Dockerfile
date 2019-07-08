FROM openjdk:11

ADD ./target/productOwner-service-0.0.1-SNAPSHOT.jar /usr/src/productOwner-service-0.0.1-SNAPSHOT.jar

WORKDIR /usr/src

ENTRYPOINT ["java","-jar", "productOwner-service-0.0.1-SNAPSHOT.jar"]