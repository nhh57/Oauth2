FROM openjdk:8
EXPOSE 8080
ADD target/test-git-action.jar test-git-action.jar
ENTRYPOINT ["java","-jar","/test-git-action.jar"]