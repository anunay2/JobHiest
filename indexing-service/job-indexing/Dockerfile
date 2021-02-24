FROM openjdk:11

ADD ./target/job-indexing-0.0.1-SNAPSHOT.jar /usr/src/job-indexing-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

ENTRYPOINT ["java","-jar", "job-indexing-0.0.1-SNAPSHOT.jar"]