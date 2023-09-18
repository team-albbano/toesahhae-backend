package com.example.toesahhae.domain.user.application.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserInfoResponseDto {
    private Long id;
    private String name;
    private LocalDate birth;

//    public String getBirth() {
//        return Optional.ofNullable(birth)
//                .map(b -> b.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
//                .orElse(null);
//
//    }
}
