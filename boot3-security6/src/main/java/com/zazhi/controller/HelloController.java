package com.zazhi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
