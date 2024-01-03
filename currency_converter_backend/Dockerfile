# Use an official OpenJDK runtime as a base image
FROM amazoncorretto:21-alpine-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container at /app/target/
COPY target/currency_converter-0.0.1.jar /app/target/currency_converter-0.0.1.jar

# Expose the port the app runs on
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "/app/target/currency_converter-0.0.1.jar"]
