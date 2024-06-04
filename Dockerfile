FROM openjdk:17-jdk-alpine
WORKDIR /ems
ADD target/springpractice-0.0.1-SNAPSHOT.jar ems.jar
EXPOSE 9090
ENTRYPOINT [ "java", "-jar", "/ems/ems.jar" ]