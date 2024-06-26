package com.jit.backend.entity;

import com.jit.backend.dto.ProductDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "productId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String factoryName;

    @Column
    private String productName;

    @Column
    private String status;

    @Column
    private Long sales;

    @Column
    private String reason;

    @Column
    private LocalDateTime createTime;

    public static Product addProduct(ProductDto productDto) {
        Product product = new Product();
        product.factoryName = productDto.getFactoryName();
        product.productName = productDto.getProductName();
        product.status = productDto.getStatus();
        product.sales = productDto.getSales();
        product.reason = productDto.getReason();
        product.createTime = LocalDateTime.now();

        return product;
    }

}
