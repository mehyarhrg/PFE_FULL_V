# FROM adoptopenjdk:11-jre-hotspot
# ARG JAR_FILE=*.jar
# COPY ${JAR_FILE} application.jar
# ENTRYPOINT ["java", "-jar", "application.jar"]

# ------------------------------------

FROM maven:3.6.1-jdk-8-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean package

# FROM openjdk:8-alpine
# COPY --from=build /workspace/target/*.jar app.jar
FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} /workspace
EXPOSE 8081
ENTRYPOINT ["java","-jar","/workspace/app.jar"]