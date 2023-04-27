package com.jit.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "매출 DTO")
public class SalesDto {

    @Schema(description = "공장이름")
    private String factoryName;

    @Schema(description = "날짜")
    private LocalDate date;

    @Schema(description = "매출")
    private Long sales;

    @Schema(description = "성공")
    private Long success;

    @Schema(description = "불량")
    private Long fail;

    @Schema(description = "생산된 물건 개수")
    private Long count;
}
