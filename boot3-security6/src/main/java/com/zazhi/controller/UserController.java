package com.zazhi.controller;

import com.zazhi.pojo.LoginDTO;
import com.zazhi.pojo.Result;
import com.zazhi.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final LoginService loginService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody LoginDTO loginDTO) {
        return Result.success(loginService.login(loginDTO));
    }
//
//    @GetMapping
//    public Result<Void> logout() {
//        loginService.logout();
//        return Result.success();
//    }
}