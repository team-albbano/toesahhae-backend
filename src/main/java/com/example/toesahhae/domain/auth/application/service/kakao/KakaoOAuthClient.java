package com.example.toesahhae.domain.auth.application.service.kakao;

import com.example.toesahhae.common.config.openfeign.OpenFeignEncodingConfig;
import com.example.toesahhae.domain.auth.application.dto.request.KakaoOAuthRequestDto;
import com.example.toesahhae.domain.auth.application.dto.response.KakaoOAuthResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "KakaoOAuthClient",
        url = "https://kauth.kakao.com",
        configuration = OpenFeignEncodingConfig.class
)
public interface KakaoOAuthClient {
    @PostMapping(value = "/oauth/token", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    KakaoOAuthResponseDto getKakaoOAuthToken(@RequestBody KakaoOAuthRequestDto kakaoOauthRequestDto);
}
