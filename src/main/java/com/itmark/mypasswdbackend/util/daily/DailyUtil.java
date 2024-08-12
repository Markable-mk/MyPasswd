package com.itmark.mypasswdbackend.util.daily;

import cn.hutool.core.map.BiMap;

import java.util.HashMap;
import java.util.Optional;

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

    /**
     * 在传入值不为空的时候进行单位转换
     * @param value
     * @return
     */
    public static Double multiplyByThousand(Double value) {
        return Optional.ofNullable(value).map(v -> v * 1000).orElse(null);
    }

}
