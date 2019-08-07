FROM openjdk:11

ADD ./target/review-service-0.0.1-SNAPSHOT.jar /writeareview/src/review-service-0.0.1-SNAPSHOT.jar

ADD ./src/main/resources/reviews.xlsx /writeareview/src/reviews.xlsx

WORKDIR /writeareview/src

ENTRYPOINT ["java","-jar", "review-service-0.0.1-SNAPSHOT.jar"]