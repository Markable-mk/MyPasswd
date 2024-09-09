package com.itmark.mypasswdbackend.service.demo.hu;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/7/30 21:57
 */
public interface HuService {
    /**
     * 1 window
     */
    void window();

    /**
     * 双向查找map
     * @param key
     * @param value
     */
    void doubleMap(String key,String value);

    /**
     * 日期工具类
     */
    void dateUtil();

    /**
     * 系统嗲用
     */
    void sysUtil();

    void pcUtil();

    /**
     * 验证
     */
    boolean validatorChinese(String txt);

    void convertUtil();
}
