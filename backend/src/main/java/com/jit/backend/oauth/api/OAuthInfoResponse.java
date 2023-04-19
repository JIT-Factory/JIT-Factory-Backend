package com.jit.backend.oauth.api;

public interface OAuthInfoResponse {
    String getEmail();
    String getName();
    OAuthProvider getOAuthProvider();
}
