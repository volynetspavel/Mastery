FROM openjdk:8-jdk-alpine
EXPOSE 8085
ADD target/mastery-0.0.1-SNAPSHOT.jar mastery-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/mastery-0.0.1-SNAPSHOT.jar"]