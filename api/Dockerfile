FROM maven:3.9.9-amazoncorretto-21 as builder
WORKDIR /opt/app

COPY src src
COPY pom.xml pom.xml

RUN mvn clean package

VOLUME /opt/app/data

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "target/api-0.0.1-SNAPSHOT.jar"]