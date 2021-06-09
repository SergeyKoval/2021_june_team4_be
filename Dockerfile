FROM maven:3.8.1-jdk-11-slim AS MAVEN_BUILD
WORKDIR /build/
COPY pom.xml /build/
COPY src /build/src

WORKDIR /build/
RUN mvn clean package
# нашел на https://hub.docker.com/
FROM openjdk:11-jre-slim

WORKDIR /app
EXPOSE 8080
COPY --from=MAVEN_BUILD /build/target/discount-1.0-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "discount-1.0-SNAPSHOT.jar"]