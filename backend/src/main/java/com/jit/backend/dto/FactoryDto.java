package com.jit.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Factory 현황관리 관리 DTO")
public class FactoryDto {

    @Schema(description = "공장 이름", example = "JIT Factory")
    private String factoryName;

    @Schema(description = "공장 상태", example = "run")
    private String factoryStatus;

    @Schema(description = "재료 투입 벨트 상태", example = "run")
    private String insultBelt;

    @Schema(description = "생산 설비 벨트 상태", example = "run")
    private String productionBelt;

    @Schema(description = "검수 1번 벨트 상태", example = "run")
    private String inspectionProductABelt;

    @Schema(description = "검수 2번 벨트 상태", example = "run")
    private String inspectionProductBBelt;

    @Schema(description = "검수 3번 벨트 상태", example = "run")
    private String inspectionProductCBelt;

}
