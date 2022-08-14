FROM openjdk:8
EXPOSE 8080
USER spring:spring
ENV JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/test-git-action.jar"]