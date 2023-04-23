package com.jit.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Process RUN/START 제어 DTO")
public class FactoryControlDto {
    @Schema(description = "공정 이름", example = "FrontProcess")
    private String processName;
}
