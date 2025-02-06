FROM maven:3.9.9-amazoncorretto-21-alpine
WORKDIR /app
COPY target/*.jar transaction-api-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","transaction-api-0.0.1-SNAPSHOT.jar"]