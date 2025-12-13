package com.zazhi.handler;

import com.alibaba.fastjson.JSON;
import com.zazhi.pojo.Result;
import com.zazhi.utils.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


/**
 *
 * @author lixh
 * @since 2025/9/18 0:45
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) {
        String jsonString = JSON.toJSONString(Result.error("认证失败"));
        WebUtil.renderString(httpServletResponse, jsonString);
    }
}
