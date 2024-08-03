package com.itmark.mypasswdbackend.service.demo.hu;

import com.itmark.mypasswdbackend.util.daily.DailyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/7/30 21:57
 */
@Slf4j
@Service
public class HuServiceImpl implements HuService{
    @Override
    public void window() {

    }

    @Override
    public void doubleMap(String key,String value) {
        String valueFromMap = DailyUtil.exceedTypeMap.get(key);
        String keyFromMap = DailyUtil.exceedTypeMap.getKey(value);
        log.info("{}，{}",valueFromMap,keyFromMap);
    }
}
