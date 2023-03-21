package com.jit.backend.entity;

import com.jit.backend.jwt.AuthDto;
import com.jit.backend.role.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String email;
    private String password;
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
    public static User registerAdmin(AuthDto.SignupDto signupDto){
        User user = new User();
        user.email = signupDto.getEmail();
        user.password = signupDto.getPassword();
        user.name = signupDto.getName();
        user.role = Role.ADMIN;

        return user;
    }
}
