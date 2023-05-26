package com.jit.backend.service;

import com.jit.backend.dto.OrdersDto;
import com.jit.backend.entity.Orders;
import com.jit.backend.entity.Product;
import com.jit.backend.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    public List<Orders> nameOfFactoryName(String factoryName){
        List<Orders> orders = ordersRepository.findAllByFactoryName(factoryName);
        return orders;
    }

    public void addOrUpdateOrder(OrdersDto ordersDto) {
        Orders order = ordersRepository.findByFactoryNameAndProductName(ordersDto.getFactoryName(), ordersDto.getProductName());

        if (order == null) {
            order = Orders.builder()
                    .productName(ordersDto.getProductName())
                    .factoryName(ordersDto.getFactoryName())
                    .count(ordersDto.getCount())
                    .deadLine(LocalDate.now())
                    .build();
        } else {
            order.updateProductOrders(ordersDto);
        }

        ordersRepository.saveAndFlush(order);
    }
}
