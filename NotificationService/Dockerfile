FROM maven:3.8.4-openjdk-17-slim AS build
ARG JAR_FILE=target/*.jar
COPY pom.xml .
COPY src ./src
RUN mvn package -DskipTests
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /target/*.jar NotificationService.jar
ENTRYPOINT ["java","-jar","NotificationService.jar"]
EXPOSE 8012