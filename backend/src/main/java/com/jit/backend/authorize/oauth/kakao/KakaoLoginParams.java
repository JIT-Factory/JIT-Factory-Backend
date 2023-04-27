package com.jit.backend.authorize.oauth.kakao;

import com.jit.backend.authorize.oauth.component.OAuthLoginParams;
import com.jit.backend.authorize.oauth.component.OAuthProvider;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class KakaoLoginParams implements OAuthLoginParams {
    @Schema
    private String authorizationCode;
    @Schema(description = "공장 이름", example = "CarFactory")
    private String factoryName;

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.KAKAO;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
        body.add("factoryName", factoryName);
        return body;
    }
}
