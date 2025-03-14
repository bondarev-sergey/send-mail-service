FROM eclipse-temurin:21
COPY build/libs/send.mail-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]