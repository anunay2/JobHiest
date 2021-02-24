FROM openjdk:11

ADD ./target/profile-indexing-0.0.1-SNAPSHOT.jar /usr/src/profile-indexing-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "profile-indexing-0.0.1-SNAPSHOT.jar"]