FROM maven:3.9.4-eclipse-temurin-21-alpine
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests
CMD ["java", "-jar", "target/device-0.0.1-SNAPSHOT.jar"]