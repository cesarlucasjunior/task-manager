FROM amazoncorretto:17-alpine-jdk
MAINTAINER Cesar Lucas Junior
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
