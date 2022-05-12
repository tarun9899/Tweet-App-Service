FROM openjdk:13-jdk-alpine
EXPOSE 8084
ARG JAR_FILE=target/tweets-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} tweets-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/tweets-0.0.1-SNAPSHOT.jar"]