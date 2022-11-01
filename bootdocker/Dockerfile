FROM adoptopenjdk/openjdk8:latest
COPY persona-0.0.1-SNAPSHOT.jar /usr/src/bootdocker/persona-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/bootdocker
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/usr/src/bootdocker/persona-0.0.1-SNAPSHOT.jar"]
