package com.jit.backend.controller;

import com.jit.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesController {

    private final ProductService productService;

    @GetMapping("/monthly")
    public ResponseEntity<Map<String, Long>> getMonthlySales() {
        Map<String, Long> monthlySales = productService.getMonthlySales();
        return ResponseEntity.ok(monthlySales);
    }

    @GetMapping("/weekly")
    public ResponseEntity<Map<String, Long>> getWeeklySales() {
        Map<String, Long> weeklySales = productService.getWeeklySales();
        return ResponseEntity.ok(weeklySales);
    }

    @GetMapping("/daily")
    public ResponseEntity<Map<String, Long>> getDailySales() {
        Map<String, Long> dailySales = productService.getDailySales();
        return ResponseEntity.ok(dailySales);
    }
}
