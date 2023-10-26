FROM openjdk:21-jdk-slim
#将本地项目jar包拷贝到Docker容器中的位置
ADD build/native ./native
ADD build/generated ./generated

EXPOSE 8080
#开机启动
ENTRYPOINT ["/native/nativeCompile/inyaw"]