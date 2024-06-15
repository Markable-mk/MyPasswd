FROM openjdk:11

ARG JAR_FILE

COPY ${JAR_FILE} myapp.jar

EXPOSE 8089

ENTRYPOINT ["java","-jar","myapp.jar","--NACOS_SERVER_HOST=xiaoma-nacos","--NACOS_SERVER_PORT=8848","NACOS_CONFIG_GROUP=mk-common-dev"]



