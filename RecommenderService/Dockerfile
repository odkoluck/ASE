# Use a base image with Maven and Java
FROM maven:3.8.4-openjdk-17-slim AS build
ARG JAR_FILE=target/*.jar
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /target/*.jar RecommenderService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","RecommenderService-0.0.1-SNAPSHOT.jar"]
EXPOSE 8089