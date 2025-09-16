package com.zazhi.service;

import com.zazhi.pojo.LoginDTO;

/**
 *
 * @author lixh
 * @since 2025/9/9 14:10
 */
public interface LoginService {
    /**
     * 用户登录
     *
     * @param loginDTO
     * @return
     */
    String login(LoginDTO loginDTO);

    /**
     * 用户登出
     */
    void logout();
}
