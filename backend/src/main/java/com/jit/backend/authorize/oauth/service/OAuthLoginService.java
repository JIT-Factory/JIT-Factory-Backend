package com.jit.backend.authorize.oauth.service;


import com.jit.backend.authorize.oauth.dto.OAuthFactoryNameDto;
import com.jit.backend.entity.User;
import com.jit.backend.authorize.jwt.AuthDto;
import com.jit.backend.authorize.oauth.component.OAuthInfoResponse;
import com.jit.backend.authorize.oauth.component.OAuthLoginParams;
import com.jit.backend.authorize.oauth.component.RequestOAuthInfoService;
import com.jit.backend.authorize.jwt.OAuthTokensGenerator;
import com.jit.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final UserRepository userRepository;
    private final OAuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthDto.TokenDto login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long userId = findOrCreateUser(oAuthInfoResponse);
        //addAffiliation(userId, factoryName);
        return authTokensGenerator.generate(userId);
    }

    private Long findOrCreateUser(OAuthInfoResponse oAuthInfoResponse) {
        return userRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(User::getId)
                .orElseGet(() -> newUser(oAuthInfoResponse));
    }

    private Long newUser(OAuthInfoResponse oAuthInfoResponse) {
        User user = User.builder()
                .email(oAuthInfoResponse.getEmail())
                .name(oAuthInfoResponse.getName())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return userRepository.save(user).getId();
    }

    public User setFactoryName(OAuthFactoryNameDto oAuthFactoryNameDto){
        User user = userRepository.findByEmail(oAuthFactoryNameDto.getEmail()).orElseThrow(()->{
            throw new IllegalStateException("없는 유저입니다.");});
        user.addAffiliation(oAuthFactoryNameDto.getFactoryName());
        userRepository.saveAndFlush(user);
        return user;
    }
}
