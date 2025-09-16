package com.zazhi.filter;

import com.zazhi.constant.RedisKey;
import com.zazhi.pojo.LoginUserDetails;
import com.zazhi.utils.JwtUtil;
import com.zazhi.utils.RedisUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

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

        // 这样创建出来的token是已认证状态
        // TODO 设置权限信息
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginUserDetails, null, null);

        SecurityContextHolder.getContext().setAuthentication(token);
        // 继续
        filterChain.doFilter(request, response);
    }
}
