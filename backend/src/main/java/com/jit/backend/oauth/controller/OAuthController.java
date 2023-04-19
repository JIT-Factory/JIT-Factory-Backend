package com.jit.backend.oauth.controller;

import com.jit.backend.oauth.api.KakaoLoginParams;
import com.jit.backend.oauth.api.NaverLoginParams;
import com.jit.backend.oauth.jwt.OAuthTokens;
import com.jit.backend.oauth.service.OAuthLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "소셜 로그인(OAuth2) 페이지", description = "네이버, 카카오 로그인 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login/oauth")
public class OAuthController {

    private final OAuthLoginService oAuthLoginService;

    @Operation(summary = "카카오 로그인", description = "카카오 회원가입 기능입니다. <br>https://kauth.kakao.com/oauth/authorize?client_id=ca246a6ff0b18bc1f0c871e985b06eee&redirect_uri=http://localhost:8080/api/oauth/kakao&response_type=code <br>위 링크를 들어가 로그인을 하면 주소창에 code=AAA 이런식으로 나올겁니다. 그걸 authorizationCode에 넣어 쓰시면 됩니다.")
    @PostMapping("/kakao")
    public ResponseEntity<OAuthTokens> loginKakao(@RequestBody KakaoLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }


    //
    @Operation(summary = "네이버 로그인", description = "네이버 회원가입 기능입니다. <br>https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=LriqiAyQwy8XlD7Wgsoe&state=hLiDdL2uhPtsftcU&redirect_uri=http://localhost:8080/api/oauth/naver <br>위 링크를 들어가 로그인을 하면 주소창에 code=AAA&status=AAA 이런 식으로 나올겁니다. code는 authorizationCode에, status는 status에 넣어 쓰시면 됩니다.")
    @PostMapping("/naver")
    public ResponseEntity<OAuthTokens> loginNaver(@RequestBody NaverLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }
}
