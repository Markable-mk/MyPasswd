-- 1 用户表
DROP TABLE IF EXISTS MARK_USER;
CREATE TABLE MARK_USER(
                        ID BIGINT(20) NOT NULL  COMMENT '用户ID' ,
                        USER_ID BIGINT(20) NOT NULL  COMMENT '用户ID' ,
                        USER_NAME VARCHAR(60) NOT NULL   COMMENT '用户名' ,
                        UER_PASSWORD VARCHAR(60) NOT NULL   COMMENT '用户密码' ,
                        USER_SALT VARCHAR(60) NOT NULL   COMMENT '密码加密盐',
                        USER_PHONE VARCHAR(60)    COMMENT '用户手机号码' ,
                        USER_EMAIL VARCHAR(60)    COMMENT '用户邮箱' ,
                        USER_REGISTER_TIME VARCHAR(20)    COMMENT '注册时间 yyyy-MM-dd HH:mm:ss' ,
                        USER_OPEN_ID VARCHAR(60)    COMMENT 'QQ唯一标识ID' ,
                        USER_QQ int(1)  COMMENT '是否绑定QQ' ,
                        PRIMARY KEY (ID)
)  COMMENT = '用户表';

CREATE INDEX MARK_USER_USER_ID ON MARK_USER(USER_ID);
CREATE INDEX MARK_USER_USER_NAME ON MARK_USER(USER_NAME);

-- 2 角色表
DROP TABLE IF EXISTS MARK_ROLE;
CREATE TABLE IF NOT EXISTS MARK_ROLE (
                                         ROLE_ID BIGINT(20) NOT NULL COMMENT '角色ID',
                                         ROLE_NAME VARCHAR(30) NOT NULL COMMENT '角色名称',
                                         ROLE_KEY VARCHAR(100) NOT NULL COMMENT '角色权限字符串',
                                         SORT_NO INT(10) NOT NULL COMMENT '显示顺序',
                                         ROLE_STATUS INT(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
                                         DEL_FLAG INT(1) DEFAULT 0 COMMENT '删除标志（0代表存在 1代表删除）',
                                         CREATE_TIME VARCHAR(20) DEFAULT NULL COMMENT '创建时间 yyyy-MM-dd HH:mm:ss',
                                         REMARK VARCHAR(500) DEFAULT NULL COMMENT '备注',
                                         PRIMARY KEY (ROLE_ID)
) ENGINE=INNODB AUTO_INCREMENT=4 DEFAULT CHARSET=UTF8 COMMENT='角色信息表';

CREATE INDEX MARK_ROLE_ROLE_STATUS ON MARK_ROLE(ROLE_STATUS);
CREATE INDEX MARK_ROLE_ROLE_DEL_FLAG ON MARK_ROLE(DEL_FLAG);

-- 3 角色用户关联表
DROP TABLE IF EXISTS MARK_USER_ROLE;
CREATE TABLE IF NOT EXISTS MARK_USER_ROLE (
                                              USER_ID BIGINT(20) NOT NULL COMMENT '用户ID',
                                              ROLE_ID BIGINT(20) NOT NULL COMMENT '角色ID',
                                              PRIMARY KEY (USER_ID,ROLE_ID)
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='用户和角色关联表';

CREATE INDEX MARK_USER_ROLE_USER_ID ON MARK_USER_ROLE(USER_ID);
CREATE INDEX MARK_USER_ROLE_ROLE_ID ON MARK_USER_ROLE(ROLE_ID);

-- 4 菜单表
DROP TABLE IF EXISTS MARK_URI_MENU;
CREATE TABLE IF NOT EXISTS MARK_URI_MENU (
                                             MENU_ID   bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
                                             MENU_NAME varchar(50) NOT NULL COMMENT '菜单名称',
                                             SORT_NO int(4) DEFAULT '0' COMMENT '显示顺序',
                                             URI_CODE varchar(200) NOT NULL COMMENT '路由',
                                             CREATE_TIME VARCHAR(20) DEFAULT NULL COMMENT '创建时间',
                                             REMARK varchar(500) DEFAULT NULL COMMENT '备注',
                                             PRIMARY KEY (MENU_ID)
) ENGINE=InnoDB AUTO_INCREMENT=1062 DEFAULT CHARSET=utf8 COMMENT='后端借口服务菜单权限表';

CREATE INDEX MARK_URI_MENU_URI_CODE ON MARK_URI_MENU(URI_CODE);

-- 5 角色菜单关系表
DROP TABLE IF EXISTS MARK_ROLE_MENU_RELA;
CREATE TABLE IF NOT EXISTS MARK_ROLE_MENU_RELA (
                                              ROLE_ID BIGINT(20) NOT NULL COMMENT '角色ID',
                                              MENU_ID BIGINT(20) NOT NULL COMMENT '菜单ID'
) ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='角色和菜单关联表';

CREATE INDEX MARK_ROLE_ROLE_ID ON MARK_ROLE_MENU_RELA(ROLE_ID);
CREATE INDEX MARK_ROLE_MENU_ID ON MARK_ROLE_MENU_RELA(MENU_ID);

-- 6 通用关联关系表，但是不要用到权限相关的
DROP TABLE IF EXISTS MARK_OBJECT_RELA;
CREATE TABLE MARK_OBJECT_RELA (
                                    ID BIGINT(20) NOT NULL COMMENT '主键ID',
                                    MASTER_ID BIGINT(20) NOT NULL COMMENT '主人公ID',
                                    LOVER_ID  BIGINT(20) NOT NULL COMMENT '喜欢者ID',
                                    MODEL_CODE VARCHAR(20) DEFAULT NULL COMMENT '模块编码',
                                    MODEL_DESC VARCHAR(20) DEFAULT NULL COMMENT '模块中文描述',
                                    PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='通用关联关系表';

CREATE INDEX MARK_OBJECT_RELA_MASTER_ID ON MARK_OBJECT_RELA(MASTER_ID);
CREATE INDEX MARK_OBJECT_RELA_LOVER_ID ON MARK_OBJECT_RELA(LOVER_ID);
CREATE INDEX MARK_OBJECT_RELA_MODEL_CODE ON MARK_OBJECT_RELA(MODEL_CODE);