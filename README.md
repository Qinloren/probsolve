<div align="center">
  <h1>🐔 刷题鸡（ProbSolve Server）</h1>
  <p>一个前后端分离的在线刷题系统 · 后端服务</p>

![](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen)
![](https://img.shields.io/badge/Java-17-blue)
![](https://img.shields.io/badge/MyBatis--Flex-1.11.1-blue)
![](https://img.shields.io/badge/MySQL-8.x-orange)
![](https://img.shields.io/badge/Redis-supported-red)

![](https://img.shields.io/github/forks/Qinloren/probsolve?style=flat)
![](https://img.shields.io/github/stars/Qinloren/probsolve?style=flat)
![](https://img.shields.io/github/issues/Qinloren/probsolve)
![](https://img.shields.io/badge/license-Apache%202.0-yellow)
</div>

---

## 📖 项目简介

**刷题鸡（ProbSolve）** 是一个基于 **前后端分离架构** 的在线刷题系统，  
本仓库为 **后端服务**，基于 **Spring Boot 3.x + Java 17** 构建。

后端主要负责：

- 用户认证与权限控制
- 题目与题库数据管理
- 刷题业务逻辑处理
- 为前端提供统一的 **REST API** 服务

---

## ✨ 当前已实现功能

> 当前版本为基础功能版本，持续迭代中

- ✅ 用户登录 / 注册（JWT）
- ✅ 权限控制（Spring Security）
- ✅ 题目与题库管理
- ✅ 刷题数据处理
- ✅ Redis 缓存支持
- ✅ 前后端接口完全分离

---

## 🧱 技术栈

### 核心框架

- **Spring Boot 3.5.7**
- **Java 17**

### 数据与持久层

- **MyBatis-Flex**
- **MySQL**
- **Druid 数据源**
- **Redis**

### 安全与认证

- **Spring Security**
- **JWT（java-jwt）**

### 其他组件

- **AOP**
- **Fastjson2 / Jackson**
- **Apache POI（Excel 导入导出）**

---

## 📁 项目结构（示意）

```text
src/main/java
├─ annotations/                 # 注解类
├─ config/                      # 配置类（安全、Redis、Web 等）
├─ controller/                  # 控制层（REST API）
├─ dto/                         # 请求数据传输对象
├─ entity/                      # 实体类
├─ exceptions/                  # 异常类
├─ filter/                      # 过滤类
├─ mapper/                      # MyBatis-Flex Mapper
├─ provider/                    # 服务提供类
├─ questions/                   # 题库处理类
├─ service/                     # 业务逻辑层
├─ vo/                          # 响应数据传输对象
└─ ProbsolveApplication.java    # 启动类
```

## 📦 快速上手

### 1.克隆项目
```bash
git clone https://github.com/Qinloren/probsolve.git
cd probsolve
```

### 2.环境要求
- **JDK**: 17
- **MySQL**: 8.x

### 3. 配置数据库与Redis
编辑`application.yml`:
```yaml
spring:
  datasource:
    # 数据库链接url
    url: jdbc:mysql://地址:端口/数据库名
    username: 用户名
    password: 密码
  data:
    redis:
      host: 地址
      port: 端口
```
完整见`src/main/resources/application.yml`文件内容

### 4.启动项目
```bash
mvn spring-boot:run
```
或
```bash
java -jar target/probsolve-版本.jar
```
启动成功后，访问`http://localhost:32223`。

## 🔐 鉴权说明
- 登录成功后，后端将携带 **Token** 返回给前端,存储于 `Authoruization`请求头中
- 请求接口时，需要在`Authorization`头信息中添加正确的 **Token** 信息
- 未携带或携带错误、过期的`Authorization`头信息视作未登录

## 🔌 前后端对接说明
- 后端以 **REST API** 形式对外提供服务
- 前端可通过 **Axios**调用接口
- 所有接口统一响应结构

## 📦 构建与部署

### 本地构建
```bash
mvn clean package
```

### Docker 构建
```bash
docker build -t 镜像名:镜像标签 .
```
注意: 最后末尾有一个小数点不可缺失

### 运行 Docker 镜像
```bash
docker -d run -p 端口:32223 镜像名:镜像标签
```

## 📄 License
本项目遵循 **Apache License 2.0** 协议。

## ⭐ 致谢
如果这个项目对你有帮助，欢迎点个 **Star** 支持一下！