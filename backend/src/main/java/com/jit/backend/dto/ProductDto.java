package com.jit.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Schema(description = "상품 DTO")
public class ProductDto {

    @Schema(description = "공장 이름", example = "CarFactory")
    private String factoryName;

    @Schema(description = "상품 이름", example = "ProductA")
    private String productName;

    @Schema(description = "상품 상태(success or fail)", example = "success")
    private String status;

    @Schema(description = "상품 매출", example = "100")
    private Long sales;

    @Schema(description = "상품 불량 이유(불량인경우만 or -)", example = "-")
    private String reason;

    @Schema(description = "상품 생산된 시간")
    private LocalDateTime createTime;

}
