package com.study.stockmanagementstudycase.auth.mapper;

import com.study.stockmanagementstudycase.auth.model.Token;
import com.study.stockmanagementstudycase.auth.model.dto.response.TokenResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TokenDTOMapper {

    public TokenResponse toResponse(
            final Token token
    ) {
        return TokenResponse.builder()
                .accessToken(token.getAccessToken())
                .accessTokenExpiresAt(token.getAccessTokenExpiresAt())
                .refreshToken(token.getRefreshToken())
                .build();
    }
}
