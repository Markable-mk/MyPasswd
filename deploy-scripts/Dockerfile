FROM openjdk:11

ARG JAR_FILE

COPY ${JAR_FILE} myapp.jar

EXPOSE 8089

ENTRYPOINT ["java","-jar","myapp.jar"]



