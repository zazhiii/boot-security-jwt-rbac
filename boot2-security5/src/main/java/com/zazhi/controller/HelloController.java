package com.zazhi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lixh
 * @since 2025/9/8 22:47
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello spring security";
    }

    @GetMapping("/a")
    public ResponseEntity<String> onlyRoleA() {
        return ResponseEntity.ok("ROLE-A访问成功");
    }

    @GetMapping("/b")
    public ResponseEntity<String> onlyRoleB() {
        return ResponseEntity.ok("ROLE-B访问成功");
    }

    @GetMapping("/pa")
    public ResponseEntity<String> onlyPermissionA() {
        return ResponseEntity.ok("PERMISSION-A访问成功");
    }

    @GetMapping("/pb")
    public ResponseEntity<String> onlyPermissionB() {
        return ResponseEntity.ok("PERMISSION-B访问成功");
    }
}
