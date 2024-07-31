package com.itmark.mypasswdbackend.service.demo.json.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/7/31 14:44
 */
@Slf4j
public class MyJsonServiceTool {
    public static String KEY_RUN_DATA = "runData";
    public static String KEY_SCORE = "score";
    public static String KEY_HISTORY = "history";
    public static String KEY_SCORE_A = "scoreA";
    public static String KEY_SCORE_B = "scoreB";
    public static String KEY_SCORE_C = "scoreC";
    public static String DEMO_JSON_ONE = "{\n" +
            "  \"runData\": {\n" +
            "    \"score\": {\n" +
            "      \"history\": [\n" +
            "        {\n" +
            "          \"scoreA\": 9.44,\n" +
            "          \"scoreB\": 2.3,\n" +
            "          \"scoreC\": 3.56,\n" +
            "          \"dataDate\": \"2024-07-31\",\n" +
            "          \"studentId\": 730845215869767721\n" +
            "        },\n" +
            "        {\n" +
            "          \"scoreA\": 3.44,\n" +
            "          \"scoreB\": 5.3,\n" +
            "          \"scoreC\": 6.56,\n" +
            "          \"dataDate\": \"2024-07-30\",\n" +
            "          \"studentId\": 123331231321321\n" +
            "        }\n" +
            "      ],\n" +
            "      \"student\": [\n" +
            "        {\n" +
            "          \"id\": \"668e58dc6338c2485aeb4591\",\n" +
            "          \"name\": \"张三\",\n" +
            "          \"customerId\": 730845215869767721\n" +
            "        },\n" +
            "        {\n" +
            "          \"id\": \"668e58dc6338c2485aeb4591\",\n" +
            "          \"name\": \"小明\",\n" +
            "          \"customerId\": 123331231321321\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  }\n" +
            "}\n";

    public static void main(String[] args) {
        // 1 解析json
        JSONObject objectFather = JSON.parseObject(DEMO_JSON_ONE);
        // 2 获取集合
        JSONArray historyScore = objectFather.getJSONObject(KEY_RUN_DATA).getJSONObject(KEY_SCORE).getJSONArray(KEY_HISTORY);
        // 3 修改集合中的值
        for (int i = 0; i < historyScore.size(); i++) {
            JSONObject singleScore = historyScore.getJSONObject(i);
            Double scoreA = singleScore.getDouble(KEY_SCORE_A);
            Double scoreB = singleScore.getDouble(KEY_SCORE_B);
            Double scoreC = singleScore.getDouble(KEY_SCORE_C);
            singleScore.put(KEY_SCORE_A,scoreA*10D);
            singleScore.put(KEY_SCORE_B,scoreB*10D);
            singleScore.put(KEY_SCORE_C,scoreC*10D);
            System.out.println(singleScore);
        }
        // 4 集合中添加一个 historyScore objectFather 均更新
        JSONObject newObj = new JSONObject();
        newObj.put(KEY_SCORE_A,100);
        newObj.put(KEY_SCORE_B,99);
        newObj.put(KEY_SCORE_C,98);
        historyScore.add(newObj);
        // 5 验证原始json是否改变，objectFather 验证已经改变了
        log.info("操作后的集合：{}",objectFather.toJSONString());
    }
}
