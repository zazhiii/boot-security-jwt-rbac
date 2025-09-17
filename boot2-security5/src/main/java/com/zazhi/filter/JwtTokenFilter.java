package com.zazhi.filter;

import com.zazhi.constant.RedisKey;
import com.zazhi.pojo.LoginUserDetails;
import com.zazhi.utils.JwtUtil;
import com.zazhi.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author lixh
 * @since 2025/9/9 21:20
 */
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String jwtToken = request.getHeader("Authorization");
        if(!StringUtils.hasText(jwtToken)){
            filterChain.doFilter(request, response);
            return;
        }
        // 解析token
        Map<String, Object> map;
        try {
             map = JwtUtil.parseToken(jwtToken);
        } catch (Exception e) {
            throw new RuntimeException("token非法");
        }
        // 从redis中获取信息
        String key = RedisKey.format(RedisKey.LOGIN, map.get("userId"));
        LoginUserDetails loginUserDetails = redisUtil.getObject(key, LoginUserDetails.class);
        if(loginUserDetails == null){
            throw new RuntimeException("用户未登录");
        }
        // 封装token，存入上下文; 这种方式创建的token是认证通过的
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginUserDetails, null, loginUserDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(request, response);
    }
}
