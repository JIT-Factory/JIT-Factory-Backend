package com.jit.backend.entity;

import com.jit.backend.dto.FactoryDto;
import com.jit.backend.dto.OrdersDto;
import com.jit.backend.dto.ProductDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "factory")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Factory {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String factoryName;

    @Column
    private String factoryStatus;

    @Column
    private String insultBelt;

    @Column
    private String productionBelt;

    @Column
    private String inspectionProductABelt;

    @Column
    private String inspectionProductBBelt;

    @Column
    private String inspectionProductCBelt;

    public void status(String status) {
        this.factoryStatus = status;
        this.insultBelt = status;
        this.productionBelt = status;
        this.inspectionProductABelt = status;
        this.inspectionProductBBelt = status;
        this.inspectionProductCBelt = status;

    }
}
