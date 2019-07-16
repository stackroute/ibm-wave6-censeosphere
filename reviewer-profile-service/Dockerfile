FROM openjdk:11

ADD ./target/reviewer-profile-0.0.1-SNAPSHOT.jar /usr/src/reviewer-profile-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "reviewer-profile-0.0.1-SNAPSHOT.jar"]