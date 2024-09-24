package com.itmark.mypasswdbackend.service.demo.gua;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/9/24 15:05
 */
@Service
@Slf4j
public class GuavaServiceImpl implements GuavaService{

    private static final RangeMap<Double, String> rangeMap;

    static {
        rangeMap = TreeRangeMap.create();

        // 向 RangeMap 添加映射关系
        rangeMap.put(Range.closed(0D, 4D), "Low");
        rangeMap.put(Range.open(5D, 10D), "Medium");
        rangeMap.put(Range.closedOpen(10D, 15D), "High");
        rangeMap.put(Range.greaterThan(15D), "Very High");
    }

    @Override
    public String getTypeByNumber(Double number) {
        return rangeMap.get(number);
    }

}
