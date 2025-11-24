package com.zazhi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zazhi
 */
@RestController
@RequestMapping("/api")
@Tag(name = "测试")
public class HelloController {
    @GetMapping(value = "/hello")
    @Operation(summary = "hello")
    public ResponseEntity<String> sayHello() {
        String message = "Hello World!";
        return ResponseEntity.ok(message);
    }

    @GetMapping("/a")
    @PreAuthorize("hasRole('ROLE-A')")
    public ResponseEntity<String> onlyRoleA() {
        return ResponseEntity.ok("ROLE-A访问成功");
    }

    @GetMapping("/b")
    @PreAuthorize("hasRole('ROLE-B')")
    public ResponseEntity<String> onlyRoleB() {
        return ResponseEntity.ok("ROLE-B访问成功");
    }

    @GetMapping("/pa")
    @PreAuthorize("hasPermission('PERMISSION-A')")
    public ResponseEntity<String> onlyPermissionA() {
        return ResponseEntity.ok("PERMISSION-A访问成功");
    }

    @GetMapping("/pb")
    @PreAuthorize("hasPermission('PERMISSION-B')")
    public ResponseEntity<String> onlyPermissionB() {
        return ResponseEntity.ok("PERMISSION-B访问成功");
    }
}
