package com.example.toesahhae.common.security.oauth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "oauth.kakao")
public class OAuthProperties {
    private final String iss;
    private final String clientId;
    private final String clientSecret;
    private final String redirectUri;
}
