package com.jit.backend.service;

import com.jit.backend.dto.OrdersDto;
import com.jit.backend.entity.Orders;
import com.jit.backend.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrdersService {
    private final OrdersRepository ordersRepository;
    public List<Orders> allOrder()  {
        List<Orders> ordersList = ordersRepository.findAll();
        return ordersList;
    }
    public void addOrUpdateOrder(OrdersDto ordersDto) {
        Orders orders = ordersRepository.findByProductName(ordersDto.getProductName());
        if(orders == null){
            orders = Orders.builder()
                    .productName(ordersDto.getProductName())
                    .status(ordersDto.getStatus()).build();
        }else{
            orders.updateProductOrders(ordersDto);
        }
        ordersRepository.saveAndFlush(orders);
    }
}
