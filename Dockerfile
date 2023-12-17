#基础镜像使用jdk1.8

FROM openjdk:8-jdk-alpine

#作者
MAINTAINER NINE

# 配置参数
ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} application.jar

ENV JAVA_XMS=512m
ENV JAVA_XMX=1024m

# 运行
CMD java -Xms ${JAVA_HEAP_MEMORY} -Xmx${JAVA_HEAP_MEMORY} -jar /application.jar

#ENTRYPOINT ["java","-jar","application.jar"]

#暴露端口
EXPOSE 3641
