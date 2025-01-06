# Use a base image with Java 17 from Zulu on Alpine Linux
FROM azul/zulu-openjdk-alpine:17

# Set working directory
WORKDIR /app

# Copy the project files into the container
COPY core/target/core-0.0.1-SNAPSHOT.jar .

# Expose the port (assuming Spring Boot application runs on port 8080)
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "core-0.0.1-SNAPSHOT.jar"]