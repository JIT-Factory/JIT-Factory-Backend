package com.jit.backend.oauth.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
@PropertySource("classpath:application.yml")
public class OAuthTokensGenerator {
    private static final String BEARER_TYPE = "Bearer";


    @Value("${jwt.access-token-validity-in-seconds}")
    private long ACCESS_TOKEN_EXPIRE_TIME;

    @Value("${jwt.refresh-token-validity-in-seconds}")
    private long REFRESH_TOKEN_EXPIRE_TIME;

    private final OAuthJwtTokenProvider jwtTokenProvider;

    public OAuthTokens generate(Long userId) {
        long now = (new Date()).getTime();
        Date accessTokenExpiredAt = new Date(now + (ACCESS_TOKEN_EXPIRE_TIME * 1000));
        Date refreshTokenExpiredAt = new Date(now + (REFRESH_TOKEN_EXPIRE_TIME * 1000));

        String subject = userId.toString();
        String accessToken = jwtTokenProvider.generate(subject, accessTokenExpiredAt);
        String refreshToken = jwtTokenProvider.generate(subject, refreshTokenExpiredAt);

        return OAuthTokens.of(accessToken, refreshToken, BEARER_TYPE, ACCESS_TOKEN_EXPIRE_TIME / 1000L);
    }

    public Long extractUserId(String accessToken) {
        return Long.valueOf(jwtTokenProvider.extractSubject(accessToken));
    }
}
