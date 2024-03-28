FROM maven:3.8.4-openjdk-17-slim AS build

COPY pom.xml ./

COPY .mvn .mvn

COPY src src

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR stockmanagement

COPY --from=build target/*.jar stockManagementStudyCase-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","stockManagementStudyCase-0.0.1-SNAPSHOT.jar"]