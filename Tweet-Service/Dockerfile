FROM openjdk:11
ADD target/spring-tweet-mongodb-docker.jar spring-tweet-mongodb-docker.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","spring-tweet-mongodb-docker.jar"]
