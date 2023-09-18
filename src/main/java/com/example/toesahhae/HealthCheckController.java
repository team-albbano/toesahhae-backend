package com.example.toesahhae;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HealthCheckController {
    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("퇴사할래!!!\n퇴사할래!!!\n퇴사할래!!!\n퇴사할래!!!\n퇴사할래!!!\n퇴사할래!!!\n퇴사할래!!!\n퇴사할래!!!\n");
    }
}
