package com.jit.backend.oauth.controller;

import com.jit.backend.oauth.api.KakaoLoginParams;
import com.jit.backend.oauth.api.NaverLoginParams;
import com.jit.backend.oauth.jwt.OAuthTokens;
import com.jit.backend.oauth.service.OAuthLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login/oauth")
public class OAuthController {

    //https://kauth.kakao.com/oauth/authorize?client_id=ca246a6ff0b18bc1f0c871e985b06eee&redirect_uri=http://localhost:8080/api/oauth/kakao&response_type=code
    private final OAuthLoginService oAuthLoginService;
    @PostMapping("/kakao")
    public ResponseEntity<OAuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }


    //https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=LriqiAyQwy8XlD7Wgsoe&state=hLiDdL2uhPtsftcU&redirect_uri=http://localhost:8080/api/oauth/naver
    @PostMapping("/naver")
    public ResponseEntity<OAuthTokens> loginNaver(@RequestBody NaverLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }
}
