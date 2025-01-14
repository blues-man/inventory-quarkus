FROM maven:3.6.3-jdk-11 AS builder
ENV PROFILE=prod
WORKDIR /opt/app
COPY . .
RUN mvn package -Dquarkus.profile=$PROFILE

FROM registry.access.redhat.com/ubi9/openjdk-17-runtime:1.13
COPY --from=builder /opt/app/target/*-runner.jar /app.jar
CMD java -jar /app.jar
EXPOSE 8080

