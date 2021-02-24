FROM openjdk:11

ADD ./target/personal-information-processing-0.0.1-SNAPSHOT.jar /usr/src/personal-information-processing-0.0.1-SNAPSHOT.jar

WORKDIR usr/src

EXPOSE 8040

ENTRYPOINT ["java","-jar", "personal-information-processing-0.0.1-SNAPSHOT.jar"]
