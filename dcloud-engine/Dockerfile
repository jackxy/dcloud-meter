# 基于selenium/standalone-chrome:4.10.0镜像构建
FROM selenium/standalone-chrome:4.10.0

# 切换到root用户
USER root
# 设置工作目录为根目录
WORKDIR /

# 更新apt源并安装curl
RUN apt-get update \
    && apt-get install -y curl

# 创建java库的目录
RUN mkdir -p /usr/local/lib/java

# 复制jre17.tar.gz到根目录并解压，然后删除压缩文件
COPY source/jre17.tar.gz /
RUN tar -xzf jre17.tar.gz -C /usr/local/lib/java \
    && rm jre17.tar.gz

# 设置JAVA_HOME环境变量和PATH
ENV JAVA_HOME=/usr/local/lib/java/jdk-17.0.10+7-jre
ENV PATH=$JAVA_HOME/bin:$PATH

# 复制apache-jmeter-5.5和dcloud-engine-1.0-SNAPSHOT-exec.jar到容器
COPY source/apache-jmeter-5.5 apache-jmeter-5.5
COPY target/dcloud-engine.jar /root

# 定义容器对外暴露的端口
EXPOSE 8082

# 定义容器启动时执行的命令
ENTRYPOINT ["java", "-jar", "/root/dcloud-engine.jar"]

