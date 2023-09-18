package com.example.toesahhae.domain.user.domain.service;

import com.example.toesahhae.domain.user.domain.entity.User;
import com.example.toesahhae.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * Social ID로 User 조회
     * @param socialId Social ID
     * @return User 객체
     */
    @Transactional
    public User createUserBySocialId(String socialId) {
        return userRepository.save(User.builder()
                .socialId(socialId)
                .build());
    }
}
