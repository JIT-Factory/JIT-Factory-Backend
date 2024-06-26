package com.jit.backend.controller;

import com.jit.backend.dto.OrdersDto;
import com.jit.backend.dto.UserDto;
import com.jit.backend.entity.Orders;
import com.jit.backend.entity.Product;
import com.jit.backend.service.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "06. 주문 페이지", description = "Unity에 주문을 할 수 있는 api 입니다.")
@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    @Operation(summary = "주문 조회", description = "Unity에서 생산중인 Product의 현황을 조회합니다.")
    @GetMapping("/all")
    public @ResponseBody
    ResponseEntity getAllOrders() {
        List<Orders> ordersList;
        try{
            ordersList = ordersService.allOrder();
        }catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(ordersList, HttpStatus.OK);
    }


    @Operation(summary = "공장 이름에 대한 주문 조회", description = "Factory Name에 해당하는 Unity에서 생산중인 Product의 현황을 조회합니다.")
    @GetMapping("/name/{factoryName}")
    public ResponseEntity nameOfProduct(
            @Parameter(description = "파라미터는 factoryName의 값을 입력합니다. <br>ex) CarFactory")
            @PathVariable ("factoryName") String factoryName) {
        List<Orders> ordersList;
        try{
            ordersList = ordersService.nameOfFactoryName(factoryName);
        }catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(ordersList, HttpStatus.OK);
    }


    @Operation(summary = "주문", description = "Unity로 Product를 추가할 수 있는 주문 기능입니다. <br>productName에 해당 이름이 존재하면 생산할 물건을 추가합니다. 없으면 Create합니다.<br>Unity는 count의 갯수만큼 Prodcut를 생산합니다.<br>Unity에서 물건을 생산을 완료하면 POST로 Count를 -1을 해주시기 바랍니다.")
    @PostMapping("/new")
    public ResponseEntity<String> addOrUpdateOrder(@RequestBody OrdersDto ordersDto) {
        ordersService.addOrUpdateOrder(ordersDto);
        return ResponseEntity.ok("Success");
    }
}
