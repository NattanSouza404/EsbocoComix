# Use Maven for building the project
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the project files
COPY pom.xml ./
COPY src ./src

# Build the application using Maven
RUN mvn clean package -DskipTests

# Use JRE for running the application
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy the fat JAR
COPY --from=build /app/target/*-jar-with-dependencies.jar app.jar
COPY --from=build /app/src/main/webapp /app/webapp

ENTRYPOINT ["java", "-jar", "app.jar"]
