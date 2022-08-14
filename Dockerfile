# syntax=docker/dockerfile:1
# which officaial Java images?
FROM openjdk:oraclelinux8
#  working directory
WORKDIR /app
# Copy from your host(pc. laptop) to container
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
# Run this inside the image
RUN ./mvnw dependency:go-offline
COPY src ./src
# run inside container
CMD ["./mvnw","spring-boot:run"]