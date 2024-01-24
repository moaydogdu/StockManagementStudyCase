package com.study.stockmanagementstudycase.security.model.mappers;

import com.study.stockmanagementstudycase.security.model.entity.TokenEntity;
import com.study.stockmanagementstudycase.security.model.entity.UserEntity;
import com.study.stockmanagementstudycase.security.model.enums.TokenType;

public class TokenMapper {

    public static TokenEntity mapForSaving(
            final UserEntity userEntity,
            final String jwtToken
    ) {
        return TokenEntity.builder()
                .user(userEntity)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
    }
}
