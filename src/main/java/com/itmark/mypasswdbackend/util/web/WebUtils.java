package com.itmark.mypasswdbackend.util.web;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * web跑龙套
 *
 * @author xiaoma
 * @date 2023/05/14
 */
public class WebUtils {
    /**
     * 1显示字符串
     *
     * @param httpServletResponse http servlet响应
     * @param msg                 味精
     */
    public static void renderString(HttpServletResponse httpServletResponse, String msg){
        httpServletResponse.setStatus(200);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        try {
            httpServletResponse.getWriter().print(msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 1正则校验请求uri
     * 其中：^标识限定开头，当有[]包围的时候表示取非
     * 其中：[\s]表示，只要出现空白就匹配 [\S]表示，非空白就匹配
     * @param permitUrlList
     * @param requesturi
     * @return
     */
    public static boolean match(List<String> permitUrlList, String requesturi) {
        if (permitUrlList == null) {
            return false;
        }
        StringBuffer urlMatch;
        for (String url : permitUrlList) {
            // 限定开头
            urlMatch = new StringBuffer("^");
            if (url.contains("**")) {
                //**用于处理路径参数的请求
                urlMatch.append(url.replace("**", "[\\s\\S]*$"));
            } else {
                urlMatch.append(url).append("$");
            }
            if (requesturi.matches(urlMatch.toString())) {
                // 返回匹配
                return true;
            }
        }
        // 返回不匹配
        return false;
    }
}
