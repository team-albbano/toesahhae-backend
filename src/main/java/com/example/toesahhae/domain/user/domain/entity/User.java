package com.example.toesahhae.domain.user.domain.entity;

import com.example.toesahhae.common.infrastructure.domain.BaseEntity;
import com.example.toesahhae.domain.user.domain.entity.enumType.RoleType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "toesahhae_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="role")
    private RoleType role;

    @Column(name="name")
    private String name;

    @Column(name="social_id", unique = true)
    private String socialId;

    @Builder
    public User(RoleType role, String name, String socialId) {
        this.role = role;
        this.name = name;
        this.socialId = socialId;
    }
}
