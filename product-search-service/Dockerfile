FROM openjdk:11

ADD ./target/product-search-service-0.0.1-SNAPSHOT.jar /usr/src/product-search-service-0.0.1-SNAPSHOT.jar

WORKDIR /usr/src

ENTRYPOINT ["java","-jar", "product-search-service-0.0.1-SNAPSHOT.jar"]