package com.jit.backend.authorize.oauth.naver;

import com.jit.backend.authorize.oauth.component.OAuthLoginParams;
import com.jit.backend.authorize.oauth.component.OAuthProvider;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Getter
@NoArgsConstructor
public class NaverLoginParams implements OAuthLoginParams {

    @Schema
    private String authorizationCode;
    @Schema
    private String state;
    @Schema(description = "공장 이름", example = "CarFactory")
    private String factoryName;

    @Override
    public OAuthProvider oAuthProvider() {
        return OAuthProvider.NAVER;
    }

    @Override
    public MultiValueMap<String, String> makeBody() {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", authorizationCode);
        body.add("state", state);
        body.add("factoryName", factoryName);
        return body;
    }
}
