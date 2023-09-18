package com.example.toesahhae;

import com.example.toesahhae.common.security.jwt.JwtProperties;
import com.example.toesahhae.common.security.oauth.OAuthProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({JwtProperties.class, OAuthProperties.class})
public class ToesahhaeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToesahhaeApplication.class, args);
    }

}
