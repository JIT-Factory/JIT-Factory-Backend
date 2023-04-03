package com.jit.backend.controller;

import com.jit.backend.dto.OrdersDto;
import com.jit.backend.dto.UserDto;
import com.jit.backend.entity.Orders;
import com.jit.backend.service.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    @GetMapping("/all")
    public @ResponseBody
    ResponseEntity getAllMember() {
        List<Orders> ordersList;
        try{
            ordersList = ordersService.allOrder();
        }catch (IllegalStateException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }return new ResponseEntity(ordersList, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<String> addOrUpdateOrder(@RequestBody OrdersDto ordersDto) {
        ordersService.addOrUpdateOrder(ordersDto);
        return ResponseEntity.ok("Success");
    }
}
