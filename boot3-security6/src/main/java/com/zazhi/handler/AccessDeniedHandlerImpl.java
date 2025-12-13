package com.zazhi.handler;

import com.alibaba.fastjson.JSON;
import com.zazhi.pojo.Result;
import com.zazhi.utils.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author lixh
 * @since 2025/9/18 0:50
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) {
        String jsonString = JSON.toJSONString(Result.error("权限不足"));
        WebUtil.renderString(httpServletResponse, jsonString);
    }
}
