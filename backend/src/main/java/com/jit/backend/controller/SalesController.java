package com.jit.backend.controller;

import com.jit.backend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "매출 및 내역 페이지", description = "매출과 내역 관련 api 입니다.")
@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesController {

    private final ProductService productService;

    @Operation(summary = "월간 내역 조회", description = "월간으로 생산된 량, 매출, 성공, 불량의 내역을 조회합니다.")
    @GetMapping("/monthly")
    public ResponseEntity<Map<String, Long>> getMonthlySales() {
        Map<String, Long> monthlySales = productService.getMonthlySales();
        return ResponseEntity.ok(monthlySales);
    }

    @Operation(summary = "주간 내역 조회", description = "주간으로 생산된 량, 매출, 성공, 불량의 내역을 조회합니다.")
    @GetMapping("/weekly")
    public ResponseEntity<Map<String, Long>> getWeeklySales() {
        Map<String, Long> weeklySales = productService.getWeeklySales();
        return ResponseEntity.ok(weeklySales);
    }

    @Operation(summary = "일간 내역 조회", description = "일간으로 생산된 량, 매출, 성공, 불량의 내역을 조회합니다.")
    @GetMapping("/daily")
    public ResponseEntity<Map<String, Long>> getDailySales() {
        Map<String, Long> dailySales = productService.getDailySales();
        return ResponseEntity.ok(dailySales);
    }

    @Operation(summary = "누적 내역 조회", description = "누적으로 생산된 량, 매출, 성공, 불량의 내역을 조회합니다.")
    @GetMapping("/all")
    public ResponseEntity<Map<String, Long>> getSumSales(){
        Map<String, Long> allSales = productService.sumSales();
        return ResponseEntity.ok(allSales);
    }
}
