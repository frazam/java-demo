# Build Stage
FROM docker.io/eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

# Runtime Stage
FROM docker.io/eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/java-demo.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
