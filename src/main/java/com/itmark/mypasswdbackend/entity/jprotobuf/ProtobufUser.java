package com.itmark.mypasswdbackend.entity.jprotobuf;

import com.baidu.bjf.remoting.protobuf.annotation.ProtobufClass;
import lombok.Data;

import java.util.Date;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/10/8 14:23
 */

@Data
@ProtobufClass
public class ProtobufUser {
    private Long id;
    private String name;
    private String trueName;
    private Integer age;
    private String sex;
    private Date createTime;
}
