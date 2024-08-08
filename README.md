A PASSWORD MANAGE SYSTEM.

# 初始化

## 0 基础中间件
```shell
 docker run -d --name my-redis  -p 16379:6379 --restart always registry.cn-hangzhou.aliyuncs.com/mk-dev/redis --requirepass "111111" --appendonly yes
 docker run -d --name my-nacos  -p 18848:8848 --restart always -e MODE=standalone registry.cn-hangzhou.aliyuncs.com/mk-dev/nacos-server
 docker run -d --name my-minio  --restart=always  -p 19000:9000  -p 19001:9001 -e "MINIO_ROOT_USER=minio123" -e "MINIO_ROOT_PASSWORD=minio123" -v /docker-vol/minio/data:/data  -v /docker-vol/minio/config:/root/.minio   registry.cn-hangzhou.aliyuncs.com/mk-dev/minio server /data --console-address ":9001"
 docker run -d --name my-mysql -p 13306:3306 --restart=always -v /docker-vol/mysql/conf:/etc/mysql/conf.d -v /docker-vol/mysql/data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root registry.cn-hangzhou.aliyuncs.com/mk-dev/mysql:5.7.25
 ```

## 1 数据库创建
``` sql
CREATE DATABASE my_passwd CHARACTER SET utf8 COLLATE utf8_general_ci;
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
    name: my-passwd-backend
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    name: defaultDataSource
    url: jdbc:mysql://192.168.9.22:13308/my_passwd?serverTimezone=PRC&characterEncoding=UTF-8&rewriteBatchedStatements=true&useSSL=false&allowMultiQueries=true
    username: root
    password: root
    type: com.alibaba.druid.pool.DruidDataSource
  redis:
    host: 192.168.9.22
    port: 16379
    password: 111111
    database: 0
  flyway:
    # flyway自动配置 true 开启
    enabled: true
    #设定 SQL 脚本的目录,多个路径使用逗号分隔, 比如取值为 classpath:db/migration,filesystem:/sql-migrations
    locations:
      - classpath:mysql/migration
    # 如果数据库不是空表，需要设置成 true，否则启动报错
    baseline-on-migrate: true
    # 与 baseline-on-migrate: true 搭配使用
    baseline-version: 0
    encoding: UTF-8
    # 迁移时是否进行校验，默认true Migration checksum mismatch
    validate-on-migrate: false
    # 版本控制日志表，默认flyway_schema_history,不同系统建议修改改数据
    table: flyway_schema_history
minio:
  # url地址
  url: http://192.168.9.22:19000
  # 用户名
  username: minio123456
  # 密码
  password: minio123456


#设置令牌加密盐
jwt:
  secret: markblog  
```