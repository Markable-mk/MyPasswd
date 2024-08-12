package com.itmark.mypasswdbackend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Locale;

/**
 * 功能描述 在项目启动时候判断连接数据库类型
 * @author 马宽
 * @date 2022/11/22 10:38
 */
@Configuration
public class InitDataSourceParam {

    public static final String DB_TYPE_ORACLE = "ORACLE";
    public static final String DB_TYPE_MYSQL = "MYSQL";
    public static String staticDataSourceType;

    @Value("${spring.datasource.url}")
    private String dataSourceType;

    @PostConstruct
    public void setStaticDataSourceType() {
        if (this.dataSourceType.contains(DB_TYPE_ORACLE.toLowerCase(Locale.ROOT))) {
            staticDataSourceType = DB_TYPE_ORACLE;
        } else {
            staticDataSourceType = DB_TYPE_MYSQL;
        }
    }


}
