package com.jit.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Factory 현황관리 관리 DTO")
public class FactoryDto {

    @Schema(description = "공장 이름", example = "JITFactory")
    private String factoryName;

    @Schema(description = "공장 상태", example = "run")
    private String factoryStatus;

    @Schema(description = "휠 벨트 상태", example = "run")
    private String conveyorBeltWheel;

    @Schema(description = "문짝 벨트 상태", example = "run")
    private String conveyorBeltDoor;

    @Schema(description = "1번 벨트 상태", example = "run")
    private String firstProcessMachineConveyorBelt;

    @Schema(description = "2번 벨트 상태", example = "run")
    private String secondProcessMachineConveyorBelt;

    @Schema(description = "3번 벨트 상태", example = "run")
    private String thirdProcessMachineConveyorBelt;

    @Schema(description = "4번 벨트 상태", example = "run")
    private String fourthProcessMachineConveyorBelt;



}
