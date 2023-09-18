package com.example.toesahhae.common.util;

import com.example.toesahhae.domain.user.domain.entity.User;
import com.example.toesahhae.domain.user.domain.service.UserFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUtils {
    private final UserFindService userFindService;

    public User getUser(){
        final Long userId = SecurityUtils.getUserId();
        return userFindService.findById(userId);
    }

}
