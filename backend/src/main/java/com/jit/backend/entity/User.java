package com.jit.backend.entity;

import com.jit.backend.dto.UserDto;
import com.jit.backend.jwt.AuthDto;
import com.jit.backend.role.Role;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static User registerUser(AuthDto.SignupDto signupDto) {
        User user = new User();
        user.email = signupDto.getEmail();
        user.password = signupDto.getPassword();
        user.name = signupDto.getName();
        user.role = Role.USER;

        return user;
    }

    public static User registerAdmin(AuthDto.SignupDto signupDto) {
        User user = new User();
        user.email = signupDto.getEmail();
        user.password = signupDto.getPassword();
        user.name = signupDto.getName();
        user.role = Role.ADMIN;

        return user;
    }
    public void changeUserRole(UserDto userDto) {
        this.role = userDto.getRole();
    }
}
