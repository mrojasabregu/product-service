FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/product-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} product-service.jar
ENTRYPOINT ["java","-jar","/product-service.jar"]