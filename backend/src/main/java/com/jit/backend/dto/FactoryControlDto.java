package com.jit.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Factory RUN/START 제어 DTO")
public class FactoryControlDto {
    @Schema(description = "공장 이름", example = "JITFactory")
    private String factoryName;
}
