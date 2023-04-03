package com.jit.backend.dto;

import com.jit.backend.jwt.AuthDto;
import com.jit.backend.role.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User DTO")
public class UserDto {

    @Schema(description = "email", example = "test@test.com")
    private String email;

    @Schema(description = "Password", example = "password")
    private String password;

    @Schema(description = "User 이름", example = "test")
    private String name;

    @Schema(description = "권한")
    private Role role;
}
