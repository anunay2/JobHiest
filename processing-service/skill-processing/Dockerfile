FROM openjdk:11

ADD ./target/skill-processing-0.0.1-SNAPSHOT.jar /usr/src/skill-processing-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

EXPOSE 8045

ENTRYPOINT ["java","-jar", "skill-processing-0.0.1-SNAPSHOT.jar"]
