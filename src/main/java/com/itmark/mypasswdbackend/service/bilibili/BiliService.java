package com.itmark.mypasswdbackend.service.bilibili;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/11/10 21:43
 */
public class BiliService {
    /**
     * 0 复制浏览器中的cookie
     */
    public static String BILIBILI_COOKIE = "";

    public static String CSRF_KEY = "bili_jct";

    public static String LIKE_URL = "https://api.bilibili.com/x/v3/fav/folder/list4navigate";
    public static String VIDEO_BELOW_LIKE_URL = "https://api.bilibili.com/x/v3/fav/resource/list4navigate?platform=web&media_id={}";

    public static String BATCH_DEL_URL = "https://api.bilibili.com/x/v3/fav/resource/batch-del";

    public static void main(String[] args) {
        // 0 获取CSRF
        String biliJctValue = getCookieValue(BILIBILI_COOKIE, CSRF_KEY);
        // 1 请求全部的收藏夹
        String body = HttpRequest.get(LIKE_URL)
                .header(Header.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .header(Header.ACCEPT_ENCODING, "gzip, deflate, br, zstd")
                .header(Header.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .header(Header.COOKIE, BILIBILI_COOKIE)
                .header(Header.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36 Edg/130.0.0.0")
                .execute().body();
        // 获取所有收藏夹
        List<Long> likeIdList = extractMediaIds(body);
        int size = likeIdList.size();
        for (int i = 0; i < size; i++) {
            Long likeId = likeIdList.get(i);
            Console.log("共{}个收藏夹，当前第：{}个收藏夹，ID：{}",size,i+1,likeId);
            extractVideoIdListFromLikeId(likeId,biliJctValue);
        }
    }

    private static void extractVideoIdListFromLikeId(Long likeId,String csrf) {
        String url = StrUtil.format(VIDEO_BELOW_LIKE_URL, likeId);
        String body = HttpRequest.get(url)
                .header(Header.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .header(Header.ACCEPT_ENCODING, "gzip, deflate, br, zstd")
                .header(Header.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .header(Header.COOKIE, BILIBILI_COOKIE)
                .header(Header.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36 Edg/130.0.0.0")
                .execute().body();

        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(body);
        } catch (Exception e) {
            Console.error("解析返回值报错，收藏夹ID：{}，错误信息：{}",likeId,e.getMessage());
            return;
        }
        JSONArray dataArray = jsonObject.getJSONArray("data");

        for (int i = 0; i < dataArray.size(); i++) {
            JSONObject dataItem = dataArray.getJSONObject(i);
            String id = dataItem.getString("id");
            String type = dataItem.getString("type");
            String title = dataItem.getString("title");
            // System.out.println(title);
            if ("已失效视频".equals(title)){
                String delBody = deleteVideo(likeId.toString(), id, type, csrf);
                Console.log("失效ID：{}，删除响应结果：{}",id,delBody);
            }
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static String deleteVideo(String likeId,String videoId,String type,String csrf){
        JSONObject object = new JSONObject();
        object.put("resources",videoId+":"+type);
        object.put("media_id",likeId);
        object.put("platform","web");
        object.put("csrf",csrf);
        // System.out.println(object.toString());
        String body = HttpRequest.post(BATCH_DEL_URL)
                .body(object.toString())
                .form("resources",videoId+":"+type)
                .form("media_id",likeId)
                .form("platform","web")
                .form("csrf",csrf)
                .header(Header.ORIGIN,"https://space.bilibili.com")
                .header(Header.REFERER,"https://space.bilibili.com/327502856/favlist?spm_id_from=333.1007.0.0")
                .header(Header.ACCEPT, "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
                .header(Header.ACCEPT_ENCODING, "gzip, deflate, br, zstd")
                .header(Header.ACCEPT_LANGUAGE, "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
                .header(Header.CONTENT_TYPE,"application/json; charset=utf-8")
                .header(Header.COOKIE, BILIBILI_COOKIE)
                .header(Header.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36 Edg/130.0.0.0")
                .execute().body();
        return body;
    }

    /**
     * 1 获取csrf
     * @param cookie
     * @param key
     * @return
     */
    public static String getCookieValue(String cookie, String key) {
        if (StrUtil.isEmpty(cookie) || StrUtil.isEmpty(key)) {
            return null;
        }

        String[] cookies = cookie.split("; ");
        for (String c : cookies) {
            String[] keyValue = c.split("=");
            if (keyValue.length == 2 && key.equals(keyValue[0])) {
                return keyValue[1];
            }
        }
        return null;
    }

    /**
     * 2 获取所有收藏夹ID
     * @param jsonString
     * @return
     */
    public static List<Long> extractMediaIds(String jsonString) {
        List<Long> ids = new ArrayList<>();
        JSONObject jsonObject = JSON.parseObject(jsonString);
        JSONArray dataArray = jsonObject.getJSONArray("data");

        for (int i = 0; i < dataArray.size(); i++) {
            JSONObject dataItem = dataArray.getJSONObject(i);
            String likeName = dataItem.getString("name");
            if (!"我创建的收藏夹".equals(likeName)){
                continue;
            }
            JSONObject mediaListResponse = dataItem.getJSONObject("mediaListResponse");
            JSONArray listArray = mediaListResponse.getJSONArray("list");
            for (int j = 0; j < listArray.size(); j++) {
                JSONObject listItem = listArray.getJSONObject(j);
                ids.add(listItem.getLong("id"));
            }
        }

        return ids;
    }

}
