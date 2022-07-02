FROM openjdk:11.0.14
# For Windows and Linux
WORKDIR /app
COPY ./gymworkouts/gymworkouts/.mvn/ .mvn
COPY ./gymworkouts/gymworkouts/mvnw ./gymworkouts/gymworkouts/pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./gymworkouts/gymworkouts/src ./src
CMD ["./mvnw", "spring-boot:run"]

# For MacOs
# ADD target/gym-workouts.jar gym-workouts.jar
# EXPOSE 8080
# ENTRYPOINT ["java", "-jar", "gym-workouts.jar"]