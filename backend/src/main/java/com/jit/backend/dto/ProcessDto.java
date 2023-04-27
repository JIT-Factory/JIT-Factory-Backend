package com.jit.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Process 현황관리 관리 DTO")
public class ProcessDto {

    @Schema(description = "공정 이름", example = "FrontProcess")
    private String processName;

    @Schema(description = "공정 상태", example = "run")
    private String processStatus;

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