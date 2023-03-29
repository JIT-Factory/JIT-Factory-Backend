package com.jit.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class DefectiveProductDto {

    private String productName;
    private String reason;
    private LocalDateTime errorTime;
}
