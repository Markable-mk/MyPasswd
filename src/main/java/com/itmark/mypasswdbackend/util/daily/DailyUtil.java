package com.itmark.mypasswdbackend.util.daily;

import cn.hutool.core.map.BiMap;

import java.util.HashMap;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/1 10:44
 */
public class DailyUtil {
    public static BiMap<String,String> exceedTypeMap;

    static {
        exceedTypeMap = new BiMap<>(new HashMap<>());
        exceedTypeMap.put("0", "VOLT_OVER_NORMAL");
        exceedTypeMap.put("1", "VOLT_OVER_UP");
        exceedTypeMap.put("2", "VOLT_OVER_LOW");
        exceedTypeMap.put("3", "VOLT_OVER_DOUBLE");
    }
}
