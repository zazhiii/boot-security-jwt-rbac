package com.zazhi.utils;

import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author lixh
 * @since 2025/9/9 11:18
 */
public class WebUtil {
    /**
     * 将字符串渲染到客户端
     * @param response
     * @param str
     * @return
     */
    public static String renderString(HttpServletResponse response, String str){
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(str);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
