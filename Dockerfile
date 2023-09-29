FROM amazoncorretto:17-alpine-jdk
MAINTAINER Cesar Lucas Junior
COPY build/libs/*.jar app.jar
COPY .env .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
