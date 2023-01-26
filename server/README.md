# 服务端
> 
# 技术选型

- [gin](https://gin-gonic.com/) web框架
- [validator](https://github.com/go-playground/validator) 参数验证
- [gorm](http://gorm.cn) 数据库操作
- [swagger](https://github.com/swaggo/swag) 接口文档
- [redis](https://redis.io/) 缓存
- [viper](https://github.com/spf13/viper) 配置读取
- [zap](https://github.com/uber-go/zap) 实现日志记录
- [MySQL](https://github.com/mysql/mysql-server) 数据库
- [mapstructure](https://github.com/mitchellh/mapstructure) 类型转换
- [websocket](github.com/gorilla/websocket) websocket套接字
- [minio](https://github.com/minio/minio-go) 对象存储minio
- [cqrs]() CQRS
- [ants](github.com/panjf2000/ants/v2) 携程池
- [ddd]() 领域驱动设计

# 快速开始

1.下载代码
```bash
git clone https://github.com/mikeygithub/devops-platform.git
```
2.安装依赖
```bash
go install
```
3.编译
```bash
go build
```
4.初始化数据库
```bash
source 
```
5.运行
```bash
./server
```

# 接口文档
```bash
swag init 
```
访问
```
http://localhost:8088/swagger/index.html#/
```



代码生成器

1.entity
2.api
3.router
4.dao

dto及其模块和文件夹
/client/dev/dto/code.go

判断当前目录下是否已经有mod，没有则一起生成
