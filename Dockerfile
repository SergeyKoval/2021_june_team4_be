FROM adoptopenjdk:11-jre-hotspot
COPY ./src/main/java/com/exadel/discount/controller/TestController.java /tmp
WORKDIR /tmp
ENTRYPOINT ["java","TestController"]