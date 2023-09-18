package com.example.toesahhae.domain.user.application.mapper;


import com.example.toesahhae.common.annotation.Mapper;
import com.example.toesahhae.domain.user.application.dto.response.UserInfoResponseDto;
import com.example.toesahhae.domain.user.domain.entity.User;

@Mapper
public class UserMapper {
    public static UserInfoResponseDto mapUserToUserInfoResponseDto(User user) {
        return UserInfoResponseDto
                .builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
