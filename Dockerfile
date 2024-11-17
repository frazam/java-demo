# Stage 1: Build
FROM docker.io/eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw clean package -DskipTests

# Stage 2: Run
FROM docker.io/eclipse-temurin:21-jre-slim
WORKDIR /app
COPY --from=build /app/target/java-demo-*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]