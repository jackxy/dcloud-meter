# 功能：构建一个基于openjdk 17-jdk-slim-buster镜像的Docker镜像，用于运行dcloud-account.jar应用。
FROM openjdk:17-jdk-slim-buster

# 将项目打包的jar文件复制到容器的root目录下
COPY target/dcloud-account.jar /root

# 定义容器对外暴露的端口，使得外部可以通过这个端口与容器内的应用进行交互
EXPOSE 8080

# 定义容器启动时执行的命令，即直接运行jar包
ENTRYPOINT ["java", "-jar", "/root/dcloud-account.jar"]