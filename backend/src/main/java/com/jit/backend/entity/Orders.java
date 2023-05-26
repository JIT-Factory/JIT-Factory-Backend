package com.jit.backend.entity;

import com.jit.backend.dto.OrdersDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Orders {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String factoryName;

    @Column
    private String productName;

    @Column(name = "count", nullable = false, updatable = true)
    private Long count;

    private LocalDate deadLine;

    public void updateProductOrders(OrdersDto ordersDto) {
        this.count += ordersDto.getCount();
    }
}
