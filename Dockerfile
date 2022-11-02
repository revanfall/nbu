#syntax = docker/dockerfile:experimental
FROM gradle:7.5.1-jdk8 as builder
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY . $APP_HOME
RUN  gradle build -x test

FROM openjdk:8-alpine as backend
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=builder $APP_HOME/build/libs/nbu-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","nbu-0.0.1-SNAPSHOT.jar"]