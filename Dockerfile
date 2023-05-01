FROM openjdk:11
EXPOSE 8080
ADD target/gradrole-0.0.1-SNAPSHOT.jar gradrole.jar
ENTRYPOINT ["java", "-jar", "/gradrole.jar"]
