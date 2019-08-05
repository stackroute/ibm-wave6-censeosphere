FROM openjdk:11

ADD ./target/reviewer-profile-0.0.1-SNAPSHOT.jar /usr/src/reviewer-profile-0.0.1-SNAPSHOT.jar

ADD ./src/main/resources/reviewer-profile.xlsx /usr/src/reviewer-profile.xlsx
ADD ./src/main/resources/reviewer-image.xlsx /usr/src/reviewer-image.xlsx

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "reviewer-profile-0.0.1-SNAPSHOT.jar"]