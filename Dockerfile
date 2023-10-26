FROM openjdk:21-jdk-slim
#将本地项目jar包拷贝到Docker容器中的位置
ADD build/libs/inyaw-0.0.1.jar ./

EXPOSE 8080
#开机启动
ENTRYPOINT ["java","-jar","/inyaw-0.0.1.jar"]