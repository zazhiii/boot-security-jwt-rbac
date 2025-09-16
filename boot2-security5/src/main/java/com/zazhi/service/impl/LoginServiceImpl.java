package com.zazhi.service.impl;

import com.zazhi.pojo.LoginDTO;
import com.zazhi.pojo.LoginUser;
import com.zazhi.pojo.User;
import com.zazhi.service.LoginService;
import com.zazhi.utils.JwtUtil;
import com.zazhi.utils.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author lixh
 * @since 2025/9/9 14:10
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;

    private final RedisUtil redisUtil;

    @Override
    public String login(LoginDTO loginDTO) {
        // 认证
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        // 认证失败
        if(authentication == null){
            throw new RuntimeException("用户名或密码错误");
        }
        // 认证成功，使用userid生成jwt，返回给前端
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        User user = loginUser.getUser();

        // 生成jwt
        Map<String, Object> map = new HashMap<>(2);
        map.put("userId", user.getId());
        map.put("username", user.getUsername());
        String jwtToken = JwtUtil.genToken(map);

        // 把完整的用户信息存入redis，userid作为key
        redisUtil.setObject("login:" + user.getId(), loginUser);

        return jwtToken;
    }

    @Override
    public void logout() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) auth.getPrincipal();
        String userId = loginUser.getUser().getId();
        redisUtil.delete("login:" + userId);
    }
}
