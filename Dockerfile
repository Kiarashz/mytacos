FROM openjdk:12

COPY /tmp/app /app

WORKDIR /app
CMD ["java", "-jar", "tacos-0.0.1-SNAPSHOT.jar"]