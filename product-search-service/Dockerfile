FROM openjdk:11

ADD ./target/product-search-service-0.0.1-SNAPSHOT.jar /usr/src/product-search-service-0.0.1-SNAPSHOT.jar

ADD ./src/main/resources/product-search.xlsx /usr/src/product-search.xlsx

WORKDIR /usr/src

ENTRYPOINT ["java","-jar", "product-search-service-0.0.1-SNAPSHOT.jar"]