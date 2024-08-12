FROM registry.cn-hangzhou.aliyuncs.com/mk-dev/openjdk:11

ARG JAR_FILE

COPY ${JAR_FILE} myapp.jar

EXPOSE 8089

ENTRYPOINT java ${JAVA_OPTS} -jar myapp.jar



