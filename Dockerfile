FROM openjdk:11
WORKDIR /
VOLUME /tmp
ADD target/vscrudate-2.0-SNAPSHOT.jar app.jar
RUN useradd -m myuser
USER myuser
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Djava.security.egd=file:/dev/.urandom","-Dspring.profiles.active=production-mode","-jar","app.jar"]
