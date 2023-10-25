FROM docker.io/debian:testing-slim
#将本地项目jar包拷贝到Docker容器中的位置
ADD build/native ./native
ADD build/resources ./resources

EXPOSE 8080
#开机启动
ENTRYPOINT ["/native/nativeCompile/inyaw"]
