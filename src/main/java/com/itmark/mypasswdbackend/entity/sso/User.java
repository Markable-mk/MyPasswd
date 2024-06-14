package com.itmark.mypasswdbackend.entity.sso;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: 马宽
 * @Date: 2022/8/16 12:34
 */
@Data
@TableName("MK_USER")
public class User implements Serializable {
    @TableId("USER_ID")
    private Long userId;
    @TableField("USER_NAME")
    private String userName;
    @TableField("UER_PASSWORD")
    private String userPassword;
    @JsonIgnore
    @TableField("USER_SALT")
    private String userSalt;
    @JsonIgnore
    @TableField("USER_OPEN_ID")
    private String userOpenId;
    @TableField("USER_QQ")
    private Boolean userQQ;
    @JsonIgnore
    // 用户菜单（接口）权限列表
    private List<String> permitMenuList;
}