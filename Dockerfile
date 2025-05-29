# Create App
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY src ./src
COPY pom.xml .
RUN mvn clean install package -DskipTests

# Final image
FROM eclipse-temurin:17-jdk-alpine
VOLUME /temp
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Profile definate with args
ARG SPRING_PROFILE=dev
ENV SPRING_PROFILES_ACTIVE=dev

ENTRYPOINT [ "java", "-jar", "app.jar" ]
