package com.jit.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {
    @GetMapping("/api/ping")
    public String ping(){
        return "pong";
    }
}
