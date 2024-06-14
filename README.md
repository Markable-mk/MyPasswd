A PASSWORD MANAGE SYSTEM.

# 初始化
## 1 数据库创建
``` sql
CREATE DATABASE my_passwd;
CREATE USER 'my_passwd'@'%' IDENTIFIED BY 'my_passwd';
grant ALL on my_passwd.* to 'my_passwd'@'%';
```

## 2 nacos配置
```yaml
server:
  port: 8089
  max-http-header-size: 16384
spring:
  application:
    name: MyPasswdBackend
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    name: defaultDataSource
    url: jdbc:mysql://192.168.1.6:13308/my_passwd?serverTimezone=PRC&characterEncoding=UTF-8&rewriteBatchedStatements=true&useSSL=false&allowMultiQueries=true
    username: my_passwd
    password: my_passwd
    type: com.alibaba.druid.pool.DruidDataSource
  flyway:
    # flyway自动配置 true 开启
    enabled: false
    #设定 SQL 脚本的目录,多个路径使用逗号分隔, 比如取值为 classpath:db/migration,filesystem:/sql-migrations
    locations:
      - classpath:db/migration
    # 如果数据库不是空表，需要设置成 true，否则启动报错
    baseline-on-migrate: true
    # 与 baseline-on-migrate: true 搭配使用
    baseline-version: 0
    encoding: UTF-8
    # 迁移时是否进行校验，默认true Migration checksum mismatch
    validate-on-migrate: false
    # 版本控制日志表，默认flyway_schema_history,不同系统建议修改改数据
    table: flyway_schema_history    
```