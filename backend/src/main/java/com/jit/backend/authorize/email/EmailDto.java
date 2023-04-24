package com.jit.backend.authorize.email;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;


@Data
public class EmailDto {

    @NotEmpty(message = "이메일을 입력해주세요")
    public String email;
}
