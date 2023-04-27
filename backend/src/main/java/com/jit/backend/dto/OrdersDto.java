package com.jit.backend.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "상품 주문 DTO")
public class OrdersDto {


    @Schema(description = "공장 이름", example = "CarFactory")
    private String factoryName;

    @Schema(description = "상품 이름", example = "ProductA")
    private String productName;

    @Schema(description = "주문 개수", example = "50")
    private Long count;

}
