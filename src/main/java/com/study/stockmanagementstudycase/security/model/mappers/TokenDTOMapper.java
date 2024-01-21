package com.study.stockmanagementstudycase.security.model.mappers;

import com.study.stockmanagementstudycase.security.model.dto.response.AuthenticationResponse;
import com.study.stockmanagementstudycase.security.model.dto.response.GenerateTokenResponse;

public class TokenDTOMapper {

    public static AuthenticationResponse toAuthenticationResponse(
            final GenerateTokenResponse generateTokenResponse
    ) {
        return AuthenticationResponse.builder()
                .accessToken(generateTokenResponse.getAccessToken())
                .refreshToken(generateTokenResponse.getRefreshToken())
                .build();
    }
}
