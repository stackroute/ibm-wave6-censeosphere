FROM openjdk:11

ADD ./target/write-a-review-0.0.1-SNAPSHOT.jar /writeareview/src/write-a-review-0.0.1-SNAPSHOT.jar

WORKDIR /writeareview/src

ENTRYPOINT ["java","-jar", "write-a-review-0.0.1-SNAPSHOT.jar"]