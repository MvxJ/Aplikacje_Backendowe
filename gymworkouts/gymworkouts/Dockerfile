FROM openjdk:11.0.14
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]

# FROM openjdk:11.0.14
# ADD target/gym-workouts.jar gym-workouts.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "gym-workouts.jar"]