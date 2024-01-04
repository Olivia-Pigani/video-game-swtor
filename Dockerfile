FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src /app/src
RUN mvn clean package

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/video-game-swtor-1.0-SNAPSHOT.jar /app/app.jar
CMD ["java","-jar", "app.jar"]