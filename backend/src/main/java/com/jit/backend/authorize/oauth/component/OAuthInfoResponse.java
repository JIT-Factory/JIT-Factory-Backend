package com.jit.backend.authorize.oauth.component;

public interface OAuthInfoResponse {
    String getEmail();
    String getName();
    OAuthProvider getOAuthProvider();
}
