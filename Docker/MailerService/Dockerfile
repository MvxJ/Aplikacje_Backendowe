FROM openjdk:11.0.14
# For Windows and Linux
WORKDIR /app
COPY ./mailer/mailer/.mvn/ .mvn
COPY ./mailer/mailer/mvnw ./mailer/mailer/pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./mailer/mailer/src ./src
CMD ["./mvnw", "spring-boot:run"]

# For MacOs
# ADD target/mailer-service.jar mailer-service.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "mailer-service.jar"]