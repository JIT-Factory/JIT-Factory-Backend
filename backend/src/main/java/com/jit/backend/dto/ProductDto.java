package com.jit.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Schema(description = "상품 DTO")
public class ProductDto {

    @Schema(description = "상품 이름", example = "ProductA")
    private String productName;

    @Schema(description = "상품 상태(success or fail)", example = "success")
    private String status;

    @Schema(description = "필요한 재료", example = "A")
    private String materialName;

    @Schema(description = "필요한 수량", example = "10")
    private Integer requireMaterial;

    @Schema(description = "상품 매출", example = "100")
    private Integer sales;

    @Schema(description = "상품 불량 이유(불량인경우만 or -)", example = "-")
    private String reason;

    @Schema(description = "상품 생산된 시간")
    private LocalDateTime createTime;

}
