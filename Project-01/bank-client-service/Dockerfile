FROM openjdk:11.0.3-jdk-slim-stretch
VOLUME /tmp
EXPOSE 8001
ARG JAR_FILE=target/bank-client-service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]