package com.jit.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "재료(재고) 관리 DTO")
public class MaterialDto {

    @Schema(description = "재료 이름", example = "A")
    private String materialName;

    @Schema(description = "재고 갯수", example = "500")
    private Integer stock;
}
