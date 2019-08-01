FROM openjdk:11

ADD ./target/product-owner-service-0.0.1-SNAPSHOT.jar /usr/src/product-owner-service-0.0.1-SNAPSHOT.jar

WORKDIR /usr/src

ENTRYPOINT ["java","-jar", "product-owner-service-0.0.1-SNAPSHOT.jar"]