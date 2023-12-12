FROM openjdk:8-jdk-alpine
EXPOSE 8089
ADD http://localhost:8081/repository/maven-releases/tn/esprit/eventsProject/0.0.1/eventsProject-1.0.0.jar /app/eventsProject.jar
ENTRYPOINT ["java","-jar","/eventsProject-1.0.0.jar"]