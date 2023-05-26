package com.jit.backend.dto;

import com.jit.backend.role.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Role DTO")
public class RoleDto {
    @Schema(description = "권한")
    private Role role;
}
