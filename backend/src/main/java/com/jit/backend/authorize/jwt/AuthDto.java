package com.jit.backend.authorize.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


public class AuthDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "Login DTO")
    public static class LoginDto {
        @Schema(description = "email", example = "test@test.com")
        private String email;
        @Schema(description = "password", example = "password")
        private String password;

        @Builder
        public LoginDto(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "Signup DTO")
    public static class SignupDto {
        @Schema(description = "email", example = "test@test.com")
        private String email;
        @Schema(description = "password", example = "password")
        private String password;
        @Schema(description = "이름", example = "Tester")
        private String name;
        @Schema(description = "소속 회사", example = "CarFactory")
        private String factoryName;

        @Builder
        public SignupDto(String email, String password, String name, String factoryName) {
            this.email = email;
            this.password = password;
            this.name = name;
            this.factoryName = factoryName;
        }

        public static SignupDto encodePassword(SignupDto signupDto, String encodedPassword) {
            SignupDto newSignupDto = new SignupDto();
            newSignupDto.email = signupDto.getEmail();
            newSignupDto.password = encodedPassword;
            newSignupDto.name = signupDto.getName();
            newSignupDto.factoryName = signupDto.getFactoryName();
            return newSignupDto;
        }
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Schema(description = "Token DTO")
    public static class TokenDto {
        @Schema(description = "Access Token")
        private String accessToken;
        @Schema(description = "Refresh Token")
        private String refreshToken;

        @Schema(description = "grantType")
        private String grantType;

        @Schema(description = "expiresIn")
        private Long expiresIn;

        public TokenDto(String accessToken, String refreshToken, String grantType, Long expiresIn) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
            this.grantType = grantType;
            this.expiresIn = expiresIn;
        }
    }
}
