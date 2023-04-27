package com.jit.backend.entity;

import com.jit.backend.authorize.oauth.component.OAuthAffiliationDto;
import com.jit.backend.dto.RoleDto;
import com.jit.backend.authorize.jwt.AuthDto;
import com.jit.backend.authorize.oauth.component.OAuthProvider;
import com.jit.backend.role.Role;
import com.zaxxer.hikari.util.ClockSource;
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

    @Column(name = "factoryName")
    private String factoryName;

    @Column
    private OAuthProvider oAuthProvider;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String email, String name, OAuthProvider oAuthProvider, String factoryName){
        this.email = email;
        this.name = name;
        this.role = Role.USER;
        this.factoryName = factoryName;
        this.oAuthProvider = oAuthProvider;
    }

    public static User registerUser(AuthDto.SignupDto signupDto) {
        User user = new User();
        user.email = signupDto.getEmail();
        user.password = signupDto.getPassword();
        user.name = signupDto.getName();
        user.factoryName = signupDto.getFactoryName();
        user.role = Role.USER;
        return user;
    }

    public static User registerAdmin(AuthDto.SignupDto signupDto) {
        User user = new User();
        user.email = signupDto.getEmail();
        user.password = signupDto.getPassword();
        user.name = signupDto.getName();
        user.factoryName = signupDto.getFactoryName();
        user.role = Role.ADMIN;

        return user;
    }
    public void changeUserRole(RoleDto roleDto) {
        this.role = roleDto.getRole();
    }

    public void addAffiliation(OAuthAffiliationDto oAuthAffiliationDto) {
        this.factoryName = oAuthAffiliationDto.getFactoryName();
    }
}
