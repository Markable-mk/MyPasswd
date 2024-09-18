package com.itmark.mypasswdbackend.service.cpolar;

import cn.hutool.http.HttpGlobalConfig;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.HttpCookie;
import java.net.URLEncoder;
import java.util.*;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/9/14 15:12
 */
@Slf4j
@Service
public class CpolarFreePathImpl implements CpolarFreePath{
    public static final String LOGIN_URL = "https://dashboard.cpolar.com/login";
    public static final String STATUS_URL = "https://dashboard.cpolar.com/status";
    public static final String KEY_NAME = "name";
    public static final String KEY_TOKEN = "csrf_token";
    public static final String KEY_VALUE = "value";
    public static final String MSG_TYPE = "text";
    public static final String UTF_EIGHT = "UTF-8";
    public static final String ALGORITHM_METHOD = "HmacSHA256";
    public static final String KEY_MAP_LOGIN = "login";
    public static final String KEY_MAP_PASSWORD = "password";

    /**
     * CPOLAR 用户名
     */
    public static final String LOGIN_USER_NAME = "xxxxxxxxxxxx";
    /**
     * CPOLAR 密码
     */
    public static final String LOGIN_USER_PWD = "xxxxxxxxxxxx";

    /**
     * 钉钉机器人TOKEN
     */
    public static final String  CUSTOM_ROBOT_TOKEN="xxxxxxxxxxxx";

    // public static void main(String[] args) {
    //     // 1 GET LOGIN PAGE
    //     String loginPage= HttpUtil.get(LOGIN_URL);
    //     // 2 GET csrf_token FROM PAGE
    //     String csrfToken = getCsrfTokenFromLoginPage(loginPage);
    //     // 3 登陆 获取COOKIE
    //     List<HttpCookie> cookies = getCookieString(csrfToken);
    //     // 4 请求在线隧道所在页面
    //     String statusPage = HttpRequest.get(STATUS_URL)
    //             .cookie(cookies).execute().body();
    //     // System.out.println(statusPage);
    //     // 5 获取隧道MAP
    //     Map<String,String> tunnelMapHttp =getTunnelMapByStatusPage(statusPage,false);
    //     Map<String,String> tunnelMapHttps =getTunnelMapByStatusPage(statusPage,true);
    //     String stringMessage = getStringMessageFromMap(tunnelMapHttp);
    //     // 6 发送消息至钉钉机器人
    //     sendTunnelMapToDingTalk(stringMessage,"CPOLARFREE",CUSTOM_ROBOT_TOKEN);
    // }

    /**
     * 拼接消息
     * @param tunnelMapHttp
     * @return
     */
    private static String getStringMessageFromMap(Map<String,String> tunnelMapHttp){
        StringBuffer stringBuffer = new StringBuffer();
        Set<String> keySet = tunnelMapHttp.keySet();
        for (String key : keySet) {
            stringBuffer.append("服务名称："+key+"，外链地址："+tunnelMapHttp.get(key)+"\n\n");
        }
        return stringBuffer.toString();
    }

    private static void sendTunnelMapToDingTalk(String message,String keyWord,String customerRobotToken) {
        try {
            // 1 拼接时间戳参数
            Long timestamp = System.currentTimeMillis();
            String secret = keyWord;
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance(ALGORITHM_METHOD);
            mac.init(new SecretKeySpec(secret.getBytes(UTF_EIGHT), ALGORITHM_METHOD));
            byte[] signData = mac.doFinal(stringToSign.getBytes(UTF_EIGHT));
            String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),UTF_EIGHT);

            //sign字段和timestamp字段必须拼接到请求URL上，否则会出现 310000 的错误信息
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?sign="+sign+"&timestamp="+timestamp);
            OapiRobotSendRequest req = new OapiRobotSendRequest();
            /**
             * 2 准备发送文本消息
             */
            //定义文本内容
            OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
            text.setContent(keyWord+"消息："+"\n\n"+message);
            // 3 定义 @ 对象 可选
            OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
            // at.setAtUserIds(Arrays.asList("cz4-n0h4c6jke"));
            //设置消息类型
            req.setMsgtype(MSG_TYPE);
            req.setText(text);
            req.setAt(at);
            // 4 发送
            OapiRobotSendResponse rsp = client.execute(req, customerRobotToken);
            log.debug("消息发送完毕，响应体为：{}",rsp.getBody());
        } catch (Exception e) {
            log.error("钉钉机器人发送消息失败！",e);
        }
    }

    /**
     * 获取隧道MAP
     * @param statusPage
     * @return
     */
    private static Map<String, String> getTunnelMapByStatusPage(String statusPage,Boolean https) {
        // Parse the HTML
        Document doc = Jsoup.parse(statusPage);

        // Extract the table rows
        Elements rows = doc.select("table tbody tr");

        // Maps to store http and https URLs
        Map<String, String> httpUrlMap = new HashMap<>();

        // Iterate through the rows and extract tunnel names and URLs
        for (Element row : rows) {
            Elements cols = row.select("td");
            String externalUrl = row.select("th").select("a").text();
            if (cols.size() >= 2) {
                // Get the tunnel name
                String tunnelName = cols.get(0).text();
                if (StringUtils.isNotEmpty(externalUrl)){
                    if (externalUrl.startsWith("tcp://")){
                        httpUrlMap.put(tunnelName, externalUrl);
                    }
                    if (externalUrl.startsWith("http://")&&!https) {
                        httpUrlMap.put(tunnelName, externalUrl);
                    }
                    if (externalUrl.startsWith("https://")&&https) {
                        httpUrlMap.put(tunnelName, externalUrl);
                    }
                }
            }
        }
        return httpUrlMap;
    }

    /**
     * 获取登陆cookie
     * @param csrfToken
     * @return
     */
    private static List<HttpCookie> getCookieString(String csrfToken) {
        Map<String, Object> loginMap = new HashMap<>();
        loginMap.put(KEY_MAP_LOGIN,LOGIN_USER_NAME);
        loginMap.put(KEY_MAP_PASSWORD,LOGIN_USER_PWD);
        loginMap.put(KEY_TOKEN, csrfToken);
        HttpResponse execute = HttpRequest.post(LOGIN_URL).form(loginMap).timeout(HttpGlobalConfig.getTimeout()).execute();
        List<HttpCookie> cookies = execute.getCookies();
        return cookies;
    }

    private static List<HttpCookie> getCookieString(String csrfToken,String userName,String password) {
        Map<String, Object> loginMap = new HashMap<>();
        loginMap.put(KEY_MAP_LOGIN,userName);
        loginMap.put(KEY_MAP_PASSWORD,password);
        loginMap.put(KEY_TOKEN, csrfToken);
        HttpResponse execute = HttpRequest.post(LOGIN_URL).form(loginMap).timeout(HttpGlobalConfig.getTimeout()).execute();
        List<HttpCookie> cookies = execute.getCookies();
        return cookies;
    }

    /**
     * 获取 csrf_token
     * @param loginPage
     * @return
     */
    private static String getCsrfTokenFromLoginPage(String loginPage) {
        Document parse = Jsoup.parse(loginPage);
        Elements elementsByAttributeValue = parse.getElementsByAttributeValue(KEY_NAME, KEY_TOKEN);
        if (Objects.nonNull(elementsByAttributeValue)){
            return elementsByAttributeValue.attr(KEY_VALUE);
        }
        return null;
    }

    @Override
    public Map<String, String> getOnLineTunnelMap(String userName, String password) {
        // 1 GET LOGIN PAGE
        String loginPage= HttpUtil.get(LOGIN_URL);
        // 2 GET csrf_token FROM PAGE
        String csrfToken = getCsrfTokenFromLoginPage(loginPage);
        // 3 登陆 获取COOKIE
        List<HttpCookie> cookies = getCookieString(csrfToken,userName,password);
        // 4 请求在线隧道所在页面
        String statusPage = HttpRequest.get(STATUS_URL)
                .cookie(cookies).execute().body();
        // 5 获取隧道MAP
        Map<String,String> tunnelMapHttp =getTunnelMapByStatusPage(statusPage,false);
        return tunnelMapHttp;
    }

    @Override
    public void sendMsgToDingTalk(String message, String accessToken, String keyWord) {
        sendTunnelMapToDingTalk(message,keyWord,accessToken);
    }

    @Override
    public void getTunnelAndSendMsgToDingTalk(String userName, String password, String accessToken, String keyWord) {
        Map<String, String> tunnelMapHttp= getOnLineTunnelMap(userName, password);
        String message=getStringMessageFromMap(tunnelMapHttp);
        sendTunnelMapToDingTalk(message,keyWord,accessToken);
    }
}
