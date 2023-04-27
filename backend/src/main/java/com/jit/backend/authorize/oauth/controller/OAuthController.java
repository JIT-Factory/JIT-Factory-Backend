package com.jit.backend.authorize.oauth.controller;

import com.jit.backend.authorize.oauth.component.OAuthAffiliationDto;
import com.jit.backend.authorize.oauth.kakao.KakaoLoginParams;
import com.jit.backend.authorize.oauth.naver.NaverLoginParams;
import com.jit.backend.authorize.oauth.service.OAuthLoginService;
import com.jit.backend.dto.MaterialDto;
import com.jit.backend.dto.RoleDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "03. 소셜 로그인(OAuth2) 페이지", description = "네이버, 카카오 로그인 관련 api 입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login/oauth")
public class OAuthController {

    private final OAuthLoginService oAuthLoginService;

    @Operation(summary = "카카오 로그인", description = "카카오 회원가입 및 로그인 기능입니다. " +
            "<br>회원이 존재하면 로그인을 시도하고 존재하지 않을 경우 회원가입을 합니다." +
            "<br>https://kauth.kakao.com/oauth/authorize?client_id=ca246a6ff0b18bc1f0c871e985b06eee&redirect_uri=http://localhost:8080/api/oauth/kakao&response_type=code " +
            "<br>위 링크를 들어가 로그인을 하면 주소창에 code=AAA 이런식으로 나올겁니다. 그걸 authorizationCode에 넣어 쓰시면 됩니다." +
            "<br>AccessToken을 헤더에 넣어서 사용할때는 꼭 \"Bearer \"(띄어쓰기 포함)을 앞에 적은다음 복붙해서 사용하셔야 합니다.")
    @PostMapping("/kakao")
    public ResponseEntity<?> loginKakao(@RequestBody KakaoLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }


    //
    @Operation(summary = "네이버 로그인", description = "네이버 회원가입 및 로그인 기능입니다. " +
            "<br>회원이 존재하면 로그인을 시도하고 존재하지 않을 경우 회원가입을 합니다." +
            "<br>https://nid.naver.com/oauth2.0/authorize?response_type=code&client_id=LriqiAyQwy8XlD7Wgsoe&state=hLiDdL2uhPtsftcU&redirect_uri=http://localhost:8080/api/oauth/naver " +
            "<br>위 링크를 들어가 로그인을 하면 주소창에 code=AAA&status=AAA 이런 식으로 나올겁니다. code는 authorizationCode에, status는 status에 넣어 쓰시면 됩니다." +
            "<br>AccessToken을 헤더에 넣어서 사용할때는 꼭 \"Bearer \"(띄어쓰기 포함)을 앞에 적은다음 복붙해서 사용하셔야 합니다.")
    @PostMapping("/naver")
    public ResponseEntity<?> loginNaver(@RequestBody NaverLoginParams params) {
        return ResponseEntity.ok(oAuthLoginService.login(params));
    }

    @Operation(summary = "OAuth로그인 회원 소속 설정", description = "OAuth로그인으로 만든 회원의 소속을 설정합니다." +
            "<br>프론트앤드에서는 OAuth로 로그인 및 회원가입시 affiliation의 null여부를 판단하시고 null인 경우 해당 경로로 이동시켜주시기 바랍니다." +
            "<br>추가 인증 방안을 생각해보면 좋을 것 같습니다. 요청이 들어왔을때 허가해주는 방식등 생각 해봅시다.")
    @PostMapping("/affiliation/{userId}")
    public ResponseEntity<String> addAffiliation(
            @Parameter(description = "파라미터는 유저의ID값을 입력합니다. <br>ex) 2")
            @PathVariable("userId") Long userId, @RequestBody OAuthAffiliationDto oAuthAffiliationDto) {
        oAuthLoginService.addAffiliation(userId, oAuthAffiliationDto);
        return ResponseEntity.ok("Add or update material success");
    }
}
