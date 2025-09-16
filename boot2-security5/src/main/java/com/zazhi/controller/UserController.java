package com.zazhi.controller;

import com.zazhi.pojo.LoginDTO;
import com.zazhi.pojo.Result;
import com.zazhi.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lixh
 * @since 2025/9/9 14:07
 */
@RestController
@RequiredArgsConstructor
public class UserController {
    private final LoginService loginService;

    @PostMapping("/user/login")
    public Result<String> login(@RequestBody LoginDTO loginDTO) {
        return Result.success(loginService.login(loginDTO));
    }

    @GetMapping
    public Result<Void> logout() {
        loginService.logout();
        return Result.success();
    }
}
