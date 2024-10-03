# Backend Dockerfile
FROM openjdk:17-jdk-alpine
VOLUME /tmp
COPY target/document-management-backend.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
