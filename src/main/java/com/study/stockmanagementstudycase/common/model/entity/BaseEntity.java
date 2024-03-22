package com.study.stockmanagementstudycase.common.model.entity;

import com.study.stockmanagementstudycase.auth.model.enums.TokenClaims;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.time.LocalDateTime;
import java.util.Optional;


@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity {

    @Column(name = "CREATED_AT")
    protected LocalDateTime createdAt;

    @Column(name = "CREATED_BY")
    protected String createdBy;

    @Column(name = "UPDATED_AT")
    protected LocalDateTime updatedAt;

    @Column(name = "UPDATED_BY")
    protected String updatedBy;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();

        this.createdBy = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(user -> !"anonymousUser".equals(user))
                .map(Jwt.class::cast)
                .map(jwt -> jwt.getClaim(TokenClaims.USER_ID.getValue()).toString())
                .orElse("SYSTEM");
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();

        this.updatedBy = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication())
                .map(Authentication::getPrincipal)
                .filter(user -> !"anonymousUser".equals(user))
                .map(Jwt.class::cast)
                .map(jwt -> jwt.getClaim(TokenClaims.USER_ID.getValue()).toString())
                .orElse("SYSTEM");
    }
}
