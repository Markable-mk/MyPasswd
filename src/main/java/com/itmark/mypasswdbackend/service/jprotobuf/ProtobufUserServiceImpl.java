package com.itmark.mypasswdbackend.service.jprotobuf;

import com.baidu.bjf.remoting.protobuf.Codec;
import com.baidu.bjf.remoting.protobuf.ProtobufProxy;
import com.itmark.mypasswdbackend.entity.jprotobuf.ProtobufUser;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Date;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/10/8 14:25
 */
@Slf4j
public class ProtobufUserServiceImpl implements ProtobufUserService{

    @Override
    public void demo() {
        ProtobufUser user = new ProtobufUser();
        user.setId(1L);
        user.setName("赵侠客");
        user.setAge(29);
        user.setSex("男");
        user.setTrueName("公众号");
        user.setCreateTime(new Date());
        //创建JProtobuf代理
        Codec<ProtobufUser> codec = ProtobufProxy.create(ProtobufUser.class);
        try {
            //使用Protobuf序列化
            byte[] bytes = codec.encode(user);
            System.out.println(bytes.length); //38
            //使用Protobuf反序列化
            ProtobufUser user1 = codec.decode(bytes);
            System.out.println(user1);
        } catch (Exception e) {
            log.error("序列化出现异常",e);
        }
    }

}
