package com.itmark.mypasswdbackend.service.demo.hu;

import cn.hutool.core.date.*;
import com.itmark.mypasswdbackend.util.daily.DailyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/7/30 21:57
 */
@Slf4j
@Service
public class HuServiceImpl implements HuService {
    @Override
    public void window() {

    }

    @Override
    public void doubleMap(String key, String value) {
        String valueFromMap = DailyUtil.exceedTypeMap.get(key);
        String keyFromMap = DailyUtil.exceedTypeMap.getKey(value);
        log.info("{}，{}", valueFromMap, keyFromMap);
    }

    @Override
    public void dateUtil() {
        // 获取今天的日期
        getToday();
        // 获取默认时间字符串
        getNowDefaultString();
        // 字符串转日期
        getStringToDate();
        // 格式化日期为字符串
        getDateToString();
        // 获取Date对象的某个部分
        getPartOfDate();
        // 开始结束时间
        getStartAndEndDayOfDate();
        // 日期时间偏移
        getDateOffset();
        // 日期时间差
        getNumOfTwoDay();
        // 获取生肖和星座
        getZodiacMethod();
        // 获取年龄
        getAgeMethod();
    }

    /**
     * 获取年龄
     */
    private void getAgeMethod() {
        //年龄
        DateUtil.ageOfNow("1990-01-30");
        //是否闰年
        DateUtil.isLeapYear(2017);
    }

    /**
     * 获取生肖和星座
     */
    private void getZodiacMethod() {
        // "摩羯座"
        String zodiac = DateUtil.getZodiac(Month.JANUARY.getValue(), 19);

        // "狗"
        String chineseZodiac = DateUtil.getChineseZodiac(1994);
    }

    /**
     * 日期时间差
     */
    private void getNumOfTwoDay() {
        String dateStr1 = "2017-03-01 22:33:23";
        Date date1 = DateUtil.parse(dateStr1);

        String dateStr2 = "2017-04-01 23:33:23";
        Date date2 = DateUtil.parse(dateStr2);

        //相差一个月，31天
        long betweenDay = DateUtil.between(date1, date2, DateUnit.DAY);

    }

    /**
     * 日期时间偏移
     */
    private void getDateOffset() {
        String dateStr = "2017-03-01 22:33:23";
        Date date = DateUtil.parse(dateStr);

        //常用偏移，结果：2017-03-04 22:33:23
        DateTime newDate2 = DateUtil.offsetDay(date, 3);

        //常用偏移，结果：2017-03-01 19:33:23
        DateTime newDate3 = DateUtil.offsetHour(date, -3);

        //昨天
        Date yesterday = DateUtil.yesterday();
        //明天
        Date tomorrow = DateUtil.tomorrow();
        //上周
        Date lastWeek = DateUtil.lastWeek();
        //下周
        Date nextWeek = DateUtil.nextWeek();
        //上个月
        Date lastMonth = DateUtil.lastMonth();
        //下个月
        Date nextMonth = DateUtil.nextMonth();

    }

    /**
     * 开始结束时间
     */
    private void getStartAndEndDayOfDate() {
        String dateStr = "2017-03-01 22:33:23";
        Date date = DateUtil.parse(dateStr);

        //一天的开始，结果：2017-03-01 00:00:00
        Date beginOfDay = DateUtil.beginOfDay(date);

        //一天的结束，结果：2017-03-01 23:59:59
        Date endOfDay = DateUtil.endOfDay(date);
    }

    /**
     * 获取Date对象的某个部分
     */
    private void getPartOfDate() {
        Date date = DateUtil.date();
        //获得年的部分
        DateUtil.year(date);
        //获得月份，从0开始计数
        DateUtil.month(date);
        //获得月份枚举
        DateUtil.monthEnum(date);
    }

    /**
     * 格式化日期为字符串
     */
    private void getDateToString() {
        String dateStr = "2017-03-01";
        Date date = DateUtil.parse(dateStr);
        //结果 2017/03/01
        String format = DateUtil.format(date, "yyyy/MM/dd");
        //常用格式的格式化，结果：2017-03-01
        String formatDate = DateUtil.formatDate(date);
        //结果：2017-03-01 00:00:00
        String formatDateTime = DateUtil.formatDateTime(date);
        //结果：00:00:00
        String formatTime = DateUtil.formatTime(date);
    }

    /**
     * 字符串转日期
     */
    private void getStringToDate() {
        String dateStr = "2017-03-01";
        Date date1 = DateUtil.parse(dateStr);
        Date date2 = DateUtil.parse(dateStr, "yyyy-MM-dd");
    }

    /**
     * 获取默认时间字符串
     */
    private void getNowDefaultString() {
        //当前时间字符串，格式：yyyy-MM-dd HH:mm:ss
        String now = DateUtil.now();
        //当前日期字符串，格式：yyyy-MM-dd
        String today= DateUtil.today();
    }

    /**
     * 获取今天的日期
     */
    private void getToday() {
        // 当前时间
        Date date = DateUtil.date();
        // 当前时间
        Date date2 = DateUtil.date(Calendar.getInstance());
        // 当前时间
        Date date3 = DateUtil.date(System.currentTimeMillis());
    }

}
