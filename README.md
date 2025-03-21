# inyaw

本项目是inyaa系列的单机版，自从其他的几个服务器到期，就不是很想搞那么一堆项目了，很麻烦。
所以这里就简单的使用spring boot + jpa + spring security，完成这样一个单机的项目。

## 使用技术

- spring boot
- jpa
- oauth2-resource（为了jwt登录，可以很少量代码完成）
- security

## 部署方式

本项目使用的github action + docker的方式部署，其他方法请各展神通
graalvm的方式经过几次尝试，不只编译的时候性能要求很高，而且还因为一些奇奇怪怪的东西失败，目前已放弃。
