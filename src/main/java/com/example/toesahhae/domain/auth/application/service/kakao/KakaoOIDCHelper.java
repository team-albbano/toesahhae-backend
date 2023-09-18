package com.example.toesahhae.domain.auth.application.service.kakao;

import com.example.toesahhae.common.exception.BusinessException;
import com.example.toesahhae.common.exception.Error;
import com.example.toesahhae.common.security.dto.OIDCDecodePayload;
import com.example.toesahhae.common.security.dto.OIDCPublicKey;
import com.example.toesahhae.common.security.dto.OIDCPublicKeysResponse;
import com.example.toesahhae.common.security.jwt.JwtOIDCProvider;
import com.example.toesahhae.common.security.oauth.OAuthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoOIDCHelper {
    private final OAuthProperties oAuthProperties;
    private final KakaoOIDCClient kakaoClient;
    private final JwtOIDCProvider jwtOIDCProvider;

    // kid를 토큰에서 가져온다.
    private String getKidFromUnsignedIdToken(String token, String iss, String aud) {
        return jwtOIDCProvider.getKidFromUnsignedTokenHeader(token, oAuthProperties.getIss(), oAuthProperties.getClientId());
    }

    public OIDCDecodePayload getPayloadFromIdToken(String token) {
        String kid = getKidFromUnsignedIdToken(token, oAuthProperties.getIss(), oAuthProperties.getClientId());
        OIDCPublicKeysResponse kakaoPublicKeys = kakaoClient.getKakaoOIDCOpenKeys();

        OIDCPublicKey kakaoPublicKey =
                kakaoPublicKeys.getKeys().stream()
                        // 같은 kid를 가져온다.
                        .filter(o -> o.getKid().equals(kid))
                        .findFirst()
                        .orElseThrow(() -> BusinessException.of(Error.KAKAO_OAUTH_FAILED3));
        // 검증이 된 토큰에서 바디를 꺼내온다.
        return jwtOIDCProvider.getOIDCTokenBody(
                token, kakaoPublicKey.getN(), kakaoPublicKey.getE());
    }
}
