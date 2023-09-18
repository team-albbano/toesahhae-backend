package com.example.toesahhae.domain.auth.application.service.kakao;

import com.example.toesahhae.common.security.dto.OIDCPublicKeysResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name = "KakaoOIDCClient",
        url = "https://kauth.kakao.com"
)
public interface KakaoOIDCClient {
    @Cacheable(cacheNames = "KakaoOIDC", cacheManager = "kakaoOidcCacheManager")
    @GetMapping("/.well-known/jwks.json")
    OIDCPublicKeysResponse getKakaoOIDCOpenKeys();
}
