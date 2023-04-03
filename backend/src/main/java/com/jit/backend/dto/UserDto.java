package com.jit.backend.dto;

import com.jit.backend.jwt.AuthDto;
import com.jit.backend.role.Role;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String email;
    private String password;
    private String name;
    private Role role;
}
