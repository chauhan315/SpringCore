# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the actual JAR file to the container
COPY target/EmployeeTaskManager-0.0.1-SNAPSHOT.jar EmployeeTaskManager.jar

ENV DOCKER_ENV=true


# Run the application
ENTRYPOINT ["java", "-jar", "EmployeeTaskManager.jar"]
