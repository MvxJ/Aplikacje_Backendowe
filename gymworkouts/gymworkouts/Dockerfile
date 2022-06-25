FROM openjdk:11.0.14

# For Windows and Linux
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]

# For MacOs
# ADD target/gym-workouts.jar gym-workouts.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "gym-workouts.jar"]