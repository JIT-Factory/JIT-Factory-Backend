package com.jit.backend.entity;

import com.jit.backend.dto.DefectiveProductDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "defectiveProduct")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DefectiveProduct {
    @Id
    @Column(name = "defectiveProduct")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productName;

    @Column
    private String reason;

    @Column
    private LocalDateTime errorTime;

    public static DefectiveProduct addLogs(DefectiveProductDto defectiveProductDto) {
        DefectiveProduct defectiveProduct = new DefectiveProduct();
        defectiveProduct.productName = defectiveProductDto.getProductName();
        defectiveProduct.reason = defectiveProductDto.getReason();
        defectiveProduct.errorTime = LocalDateTime.now();
        return defectiveProduct;
    }
}
