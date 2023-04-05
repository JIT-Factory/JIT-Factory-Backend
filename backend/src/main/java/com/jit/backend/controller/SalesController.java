package com.jit.backend.controller;

import com.jit.backend.dto.ProductDto;
import com.jit.backend.dto.SalesDto;
import com.jit.backend.dto.UserDto;
import com.jit.backend.entity.Product;
import com.jit.backend.entity.Sales;
import com.jit.backend.service.ProductService;
import com.jit.backend.service.SalesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "매출 및 내역 페이지", description = "매출과 내역 관련 api 입니다.")
@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;


    @Operation(summary = "월간 내역 조회", description = "월간으로 생산된 량, 매출, 성공, 불량의 내역을 조회합니다.")
    @GetMapping("/monthly")
    public ResponseEntity<Map<String, Long>> getMonthlySales() {
        Map<String, Long> monthlySales = salesService.getMonthlySales();
        return ResponseEntity.ok(monthlySales);
    }

    @Operation(summary = "주간 내역 조회", description = "주간으로 생산된 량, 매출, 성공, 불량의 내역을 조회합니다.")
    @GetMapping("/weekly")
    public ResponseEntity<Map<String, Long>> getWeeklySales() {
        Map<String, Long> weeklySales = salesService.getWeeklySales();
        return ResponseEntity.ok(weeklySales);
    }

    @Operation(summary = "일간 내역 조회", description = "일간으로 생산된 량, 매출, 성공, 불량의 내역을 조회합니다.")
    @GetMapping("/daily")
    public ResponseEntity<Map<String, Long>> getDailySales() {
        Map<String, Long> dailySales = salesService.getDailySales();
        return ResponseEntity.ok(dailySales);
    }

    @Operation(summary = "누적 내역 조회", description = "누적으로 생산된 량, 매출, 성공, 불량의 내역을 조회합니다.")
    @GetMapping("/all")
    public ResponseEntity<Map<String, Long>> getSumSales(){
        Map<String, Long> allSales = salesService.sumSales();
        return ResponseEntity.ok(allSales);
    }

    @Operation(summary = "날짜별 내역 조회", description = "날짜별로 생산된 량, 매출, 성공, 불량의 내역을 조회합니다.")
    @GetMapping("/date")
    public ResponseEntity<List<Sales>> getSalesByDate() {
        List<Sales> salesList = salesService.getSalesByDate();
        return ResponseEntity.ok(salesList);
    }

    @Operation(summary = "특정 날짜별 조회", description = "특정 날짜별 생산된 량, 매출, 성공, 불량의 내역을 조회합니다.")
    @GetMapping("/date/{date}")
    public ResponseEntity<Sales> salesByDate(@PathVariable("date") LocalDate date) {
        Sales sales = salesService.salesByDate(date);
        return ResponseEntity.ok(sales);
    }

}
