FROM openjdk:8
RUN addgroup -S spring && adduser -S spring -G spring
EXPOSE 8080
USER spring:spring
ENV JAR_FILE=*.jar
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-jar","/test-git-action.jar"]