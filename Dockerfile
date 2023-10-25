FROM openjdk:17-jdk-alpine
#将本地项目jar包拷贝到Docker容器中的位置
ADD build/native/nativeCompile/* ./

EXPOSE 8080
#开机启动
ENTRYPOINT ["inyaw"]
