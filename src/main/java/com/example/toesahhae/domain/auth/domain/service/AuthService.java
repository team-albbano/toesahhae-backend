package com.example.toesahhae.domain.auth.domain.service;

import com.example.toesahhae.common.exception.BusinessException;
import com.example.toesahhae.common.exception.Error;
import com.example.toesahhae.common.security.jwt.JwtProvider;
import com.example.toesahhae.common.security.oauth.OAuthProperties;
import com.example.toesahhae.domain.auth.application.dto.request.KakaoOAuthRequestDto;
import com.example.toesahhae.domain.auth.application.dto.request.RefreshTokensRequestDto;
import com.example.toesahhae.domain.auth.application.dto.response.KakaoOAuthResponseDto;
import com.example.toesahhae.domain.auth.application.dto.response.TokenResponseDto;
import com.example.toesahhae.domain.auth.application.service.kakao.KakaoOAuthClient;
import com.example.toesahhae.domain.auth.application.service.kakao.KakaoOIDCHelper;
import com.example.toesahhae.domain.user.domain.entity.User;
import com.example.toesahhae.domain.user.domain.service.UserFindService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuthService {
    private final KakaoOAuthClient kakaoOAuthClient;
    private final KakaoOIDCHelper kakaoOIDCHelper;
    private final JwtProvider jwtProvider;
    private final OAuthProperties oAuthProperties;
    private final UserFindService userFindService;

    /**
     * 카카오 인가 코드로 id_token을 받아옴
     * @param code 카카오 인가 코드
     * @return ID Token 값
     * @throws BusinessException Error.KAKAO_OAUTH_FAILED4 인가 코드가 유효하지 않은 경우
     */
    private String getIdToken(String code) {
        KakaoOAuthRequestDto kakaoOAuthRequestDto = KakaoOAuthRequestDto.builder()
                .client_id(oAuthProperties.getClientId())
                .client_secret(oAuthProperties.getClientSecret())
                .redirect_uri(oAuthProperties.getRedirectUri())
                .code(code)
                .build();

        KakaoOAuthResponseDto responseDto = null;
        try {
            responseDto = kakaoOAuthClient.getKakaoOAuthToken(kakaoOAuthRequestDto);
        } catch (Exception e) {
            log.error("카카오 OAuth 토큰 요청 실패", e);
            throw BusinessException.of(Error.KAKAO_OAUTH_FAILED4);
        }
        return responseDto.getId_token();
    }

    /**
     * 카카오 인가 코드로 accessToken과 refreshToken을 생성
     * @param code 카카오 인가 코드
     * @return accessToken과 refreshToken을 담은 DTO
     */
    public TokenResponseDto generateTokens(final String code) {
        String idToken = getIdToken(code);
        String socialId = kakaoOIDCHelper.getPayloadFromIdToken(idToken).getSub();
        User user = userFindService.findOrCreateBySocialId(socialId);
        //id_token 디코딩 후 Sub값의 uuid로 accessToken 생성

        return TokenResponseDto.builder()
                .accessToken(jwtProvider.generateAccessToken(user.getId()))
                .refreshToken(jwtProvider.generateRefreshToken(user.getId()))
                .build();
    }

    /**
     * 리프레시 토큰으로 accessToken과 refreshToken을 재발급
     * @param refreshTokenDto refreshToken을 담은 DTO
     * @return accessToken과 refreshToken을 담은 DTO
     */
    public TokenResponseDto refreshTokens(final RefreshTokensRequestDto refreshTokenDto) {
        final String refreshToken = refreshTokenDto.getRefreshToken();
        jwtProvider.validateRefreshToken(refreshToken); // 저장된 리프레시와 받은 리프레시가 일치하는 지 검증

        Long userId = jwtProvider.extractId(refreshToken); // 리프레시 토큰에 담긴 userId가 실제로 존재하는 지 검증
        userFindService.validateUserId(userId);

        return TokenResponseDto.builder()
                .accessToken(jwtProvider.generateAccessToken(userId))
                .refreshToken(jwtProvider.generateRefreshToken(userId))
                .build();
    }
}
