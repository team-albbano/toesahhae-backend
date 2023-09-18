package com.example.toesahhae.domain.user.presentation;

import com.example.toesahhae.common.dto.ResponseDto;
import com.example.toesahhae.domain.user.application.dto.response.UserInfoResponseDto;
import com.example.toesahhae.domain.user.application.service.UserSearchUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Validated
public class UserController {
    private final UserSearchUseCase userSearchUseCase;

    @GetMapping("/info")
    public ResponseEntity<ResponseDto<UserInfoResponseDto>> getUserInfo() {
        return ResponseEntity.ok(ResponseDto.success(userSearchUseCase.getUserInfo()));
    }

}
