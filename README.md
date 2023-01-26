# devops-platform

<p align="center">
  <a href="https://github.com/ken-xue/devops-platform/blob/master/LICENSE">
    <img src="https://img.shields.io/static/v1?label=license&message=MIT&color=green">
  </a>
  <a href="https://github.com/ken-xue/devops-platform/blob/master/LICENSE">
    <img src="https://img.shields.io/static/v1?label=JDK&message=17&color=red">
  </a>
  <a href="https://github.com/ken-xue/devops-platform/blob/master/LICENSE">
    <img src="https://img.shields.io/static/v1?label=SpringBoot&message=3&color=green">
  </a>
  <a href="https://kubernetes.io">
    <img src="https://img.shields.io/static/v1?label=kubernetes&message=1.2x&color=blue">
  </a>
  <a href="https://github.com/ken-xue/devops-platform/blob/master/LICENSE">
    <img src="https://img.shields.io/static/v1?label=Jsch&message=0.1.55&color=red">
  </a>  
  <a href="https://github.com/ken-xue/devops-platform/blob/master/LICENSE">
    <img src="https://img.shields.io/static/v1?label=mybatis-plus&message=3.5&color=blue">
  </a>
  <a href="https://github.com/ken-xue/devops-platform/blob/master/LICENSE">
    <img src="https://img.shields.io/static/v1?label=minio&message=8.3.3&color=red">
  </a>  
</p>

<p align="center">
<a title="GitHub Watchers" target="_blank" href="https://github.com/ken-xue/devops-platform/watchers"><img alt="GitHub Watchers" src="https://img.shields.io/github/watchers/ken-xue/devops-platform.svg?label=Watchers&style=social"></a>  
  <a title="GitHub Stars" target="_blank" href="https://github.com/ken-xue/devops-platform/stargazers"><img alt="GitHub Stars" src="https://img.shields.io/github/stars/ken-xue/devops-platform.svg?label=Stars&style=social"></a>  
  <a title="GitHub Forks" target="_blank" href="https://github.com/ken-xue/devops-platform/network/members"><img alt="GitHub Forks" src="https://img.shields.io/github/forks/ken-xue/devops-platform.svg?label=Forks&style=social"></a>
</p>

<p align="center"> 中文简体  |  <a title="English" href="README_en.md"> English</a></p>



## 项目简介

> devops-platform 是一款企业级开发、效能、运维平台。打通项目立项、研发、测试、发布、运维等流程，对软件项目开发形成闭环。前后端分离开发[前端项目地址](https://github.com/ken-xue/devops-platform-vue)

## 项目架构

![image.png](doc/image/ddd-layer.png)

## 快速开始

1.下载代码
```bash
git clone https://github.com/ken-xue/devops-platform.git
```
2.创建数据库
```bash
create database `devops-platform` default charset utf8mb4
```
3.导入SQL脚本
```bash
use `devops-platform`
source /Users/biaoyang/IdeaProjects/devops-platform/doc/script/DDL.sql
```

4.配置对象存储地址

```yaml
# minio访问地址
minio.url=http://192.168.100.21:30001/
# 用户名
minio.accessKey=minioadmin
# 密码
minio.secretKey=minioadmin
# 配置构建服务器
mc config host add minio http://192.168.100.21:30001 minioadmin minioadmin
```

5.编译并运行
```bash
cd devops-platform
mvn clean install
```

6.构建服务器配置环境

- minio客户端mc (需配置好连接服务器)
- maven
- git
- jre

## 项目文档

- [详细设计文档](https://www.yuque.com/docs/share/2a5531ff-f74d-48a5-922a-226c52abd755)

## 相关许可

MIT [LICENSE](./LICENSE)

## 项目图片

![img.png](doc/image/machine-img.png)

![img.png](doc/image/pipeline-list.png)

![img.png](doc/image/pipeline-img.png)

![img.png](doc/image/pipeline-node-logger.png)

![img.png](doc/image/pipeline-logger.png)

![img.png](doc/image/minio-create.png)

![img.png](doc/image/project-task.png)