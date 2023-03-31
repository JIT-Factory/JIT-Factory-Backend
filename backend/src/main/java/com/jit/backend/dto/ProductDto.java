package com.jit.backend.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ProductDto {

    private String productName;

    private String status;

    private Integer sales;

    private String reason;

    private LocalDateTime createTime;


}
