# Use an official Maven image as the base image
FROM maven:3.8.4-openjdk-17-slim AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the project's pom.xml and download the dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the project source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Use a lightweight Java image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/target/export-service.jar .

EXPOSE 8082

# Specify the command to run the Spring Boot application
CMD ["java", "-jar", "export-service.jar"]
