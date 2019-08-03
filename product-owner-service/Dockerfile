FROM openjdk:11

ADD ./target/product-owner-service-0.0.1-SNAPSHOT.jar /usr/src/product-owner-service-0.0.1-SNAPSHOT.jar

ADD ./src/main/resources/product-owner-profile.xlsx /usr/src/product-owner-profile.xlsx

WORKDIR /usr/src

ENTRYPOINT ["java","-jar", "product-owner-service-0.0.1-SNAPSHOT.jar"]