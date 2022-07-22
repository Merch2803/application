FROM openjdk:11.0.11-jre-slim-buster
RUN apt update && apt install -y curl
COPY ./build/libs/config-server.jar configserver.jar
CMD ["java", "-jar", "configserver.jar"]