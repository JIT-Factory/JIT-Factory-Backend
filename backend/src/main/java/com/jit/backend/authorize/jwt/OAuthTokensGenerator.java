package com.jit.backend.authorize.jwt;

import com.jit.backend.entity.User;
import com.jit.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthTokensGenerator {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    public AuthDto.TokenDto generate(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()->{
            throw new IllegalStateException("User 정보를 다시 확인하세요");});

        return jwtTokenProvider.createToken(user.getEmail(), user.getRole().toString());
    }


}