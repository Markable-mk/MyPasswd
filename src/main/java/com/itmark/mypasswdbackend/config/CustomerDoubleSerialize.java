package com.itmark.mypasswdbackend.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * @Description: 使用自定义序列化器来格式化返回值保留小数，使用方法@JsonSerialize(using = CustomerDoubleSerialize.class)
 * @Author: 马宽
 * @Date: 2022/10/27 21:09
 */
public class CustomerDoubleSerialize extends JsonSerializer<Double> {

    private static final String TWO_NUM = "%.2f";

    @Override
    public void serialize(Double aDouble, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(aDouble != null) {
            jsonGenerator.writeString(String.format(TWO_NUM,aDouble));
        }
    }
}
