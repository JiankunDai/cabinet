### 东北林业大学图书馆书包柜预约系统 

### 开发工具

IntelliJ IDEA 2019.1
Postman
Navicat Premium 12
Apache JMeter 5.1.1


### 开发环境

| JDK | Maven| Mysql  | SpringBoot | redis | RabbitMQ| Mybatis |
| :-------------: |:-------------:| :-------------:| :-------------:|:-------------:| :-------------:|
| 1.8  | 3.2.2 | 8.0.16| 2.1.5.RELEASE | 3.1.0 | 4.X | 3.5.1 |

### 项目启动说明

1、启动前，请配置 application.properties 中相关redis、mysql、rabbitmq地址。

2、登录地址：http://localhost:8080/api/login   


### 其它说明

1、数据库共有一千个学生左右（学号：从2016211000~2016211999 密码为：123456），为压测准备的。（使用 cn.edu.nefu.lib.util.UserUtil.java该类生成的，生成token做压测也是在此类里面）

2、优化如下：

#### 组织架构图


### 开发者信息
