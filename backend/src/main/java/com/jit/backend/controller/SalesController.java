package com.jit.backend.controller;

import com.jit.backend.dto.ProductDto;
import com.jit.backend.dto.SalesDto;
import com.jit.backend.dto.UserDto;
import com.jit.backend.entity.Product;
import com.jit.backend.entity.Sales;
import com.jit.backend.service.ProductService;
import com.jit.backend.service.SalesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@Tag(name = "08. 매출 및 내역 페이지", description = "매출과 내역 관련 api 입니다.")
@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    @Operation(summary = "전체 매출 조회", description = "전체 매출을 조회합니다.")
    @GetMapping("/show")
    public ResponseEntity<List<?>> sales() {
        List<Sales> sales = salesService.allSales();
        return ResponseEntity.ok(sales);
    }

    @Operation(summary = "공장 이름에 대한 매출 조회", description = "Factory Name에 해당하는 상품의 생산 날짜, 매출, 성공, 실패, 생산 내역을 조회합니다.")
    @GetMapping("/name/{factoryName}")
    public ResponseEntity nameOfSales(
            @Parameter(description = "파라미터는 factoryName의 값을 입력합니다. <br>ex) CarFactory")
            @PathVariable ("factoryName") String factoryName) {
        List<Sales> salesList;
        try{
            salesList = salesService.nameOfFactoryName(factoryName);
        }catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(salesList, HttpStatus.OK);
    }

    @Operation(summary = "공장 이름에 대한 월간 매출 조회", description = "Factory Name에 해당하는 상품의 생산 날짜, 매출, 성공, 실패, 생산 내역을 조회합니다." +
            "<br>파라미터는 factoryName, 조회할 year, month를 입력합니다." +
            "<br> ex) CarFactory" +
            "<br> ex) 2023" +
            "<br> ex) 04")
    @GetMapping("/month/{factoryName}/{year}/{month}")
    public List<Sales> getSalesByMonth(@PathVariable String factoryName, @PathVariable int year, @PathVariable int month) {
        return salesService.getSalesByMonth(factoryName, year, month);
    }

    @Operation(summary = "공장 이름에 대한 최근 한달에 대한 매출 조회", description = "Factory Name에 해당하는 상품의 생산 날짜, 매출, 성공, 실패, 생산 내역을 조회합니다." +
            "<br>파라미터는 factoryName의 값을 입력합니다." +
            "<br> ex) CarFactory")
    @GetMapping("/month/{factoryName}")
    public List<Sales> getSalesForPastMonth(@PathVariable String factoryName) {
        return salesService.getSalesForPastMonth(factoryName);
    }

    @Operation(summary = "공장 이름에 대한 최근 1주간 매출 조회", description = "Factory Name에 해당하는 상품의 생산 날짜, 매출, 성공, 실패, 생산 내역을 조회합니다." +
            "<br>파라미터는 factoryName의 값을 입력합니다." +
            "<br> ex) CarFactory")
    @GetMapping("/week/{factoryName}")
    public List<Sales> getSalesForPastWeek(@PathVariable String factoryName) {
        return salesService.getSalesForPastWeek(factoryName);
    }

    @Operation(summary = "공장 이름에 대한 당일 매출 조회", description = "Factory Name에 해당하는 상품의 생산 날짜, 매출, 성공, 실패, 생산 내역을 조회합니다." +
            "<br>파라미터는 factoryName의 값, 조회할 날짜를 입력합니다." +
            "<br> ex) CarFactory" +
            "<br> ex) 2023-04-27")
    @GetMapping("/day/{factoryName}/{date}")
    public List<Sales> getSalesByDay(@PathVariable String factoryName, @PathVariable LocalDate date) {
        return salesService.getSalesByDay(factoryName, date);
    }

}
