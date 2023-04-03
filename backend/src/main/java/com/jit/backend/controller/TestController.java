package com.jit.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "테스트 페이지", description = "테스트 페이지 입니다.")
@RestController
public class TestController {
    @Operation(summary = "핑퐁", description = "핑퐁")
    @GetMapping("/api/ping")
    public String pong() {
        return "Pong!";
    }
}
