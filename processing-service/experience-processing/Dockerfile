FROM openjdk:11

ADD ./target/experience-processing-0.0.1-SNAPSHOT.jar /usr/src/experience-processing-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

EXPOSE 8035

ENTRYPOINT ["java","-jar", "experience-processing-0.0.1-SNAPSHOT.jar"]
