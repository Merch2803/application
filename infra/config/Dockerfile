FROM openjdk:11.0.11-jre-slim-buster
COPY ./build/libs/config-1.0-SNAPSHOT.jar configserver.jar
CMD ["java", "-jar", "configserver.jar"]