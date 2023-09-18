package com.example.toesahhae.domain.user.application.service;

import com.example.toesahhae.common.annotation.UseCase;
import com.example.toesahhae.common.util.UserUtils;
import com.example.toesahhae.domain.user.application.dto.response.UserInfoResponseDto;
import com.example.toesahhae.domain.user.application.mapper.UserMapper;
import com.example.toesahhae.domain.user.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@UseCase
public class UserSearchUseCase {
    private final UserUtils userUtils;
    private final UserService userService;

    public UserInfoResponseDto getUserInfo() {
        return UserMapper.mapUserToUserInfoResponseDto(userUtils.getUser());
    }

}
