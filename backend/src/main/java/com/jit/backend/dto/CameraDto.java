package com.jit.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "카메라 컨트 DTO")
public class CameraDto {

    @Schema(description = "공장 이름", example = "CarFactory")
    String factoryName;

    @Schema(description = "카메라 위치", example = "1")
    Long cameraNumber;
}
