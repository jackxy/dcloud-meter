FROM openjdk:17-jdk-slim-buster

COPY target/dcloud-data.jar /root

# 定义容器对外暴露的端口
EXPOSE 8081

# 定义容器启动时执行的命令
ENTRYPOINT ["java", "-jar", "/root/dcloud-data.jar"]