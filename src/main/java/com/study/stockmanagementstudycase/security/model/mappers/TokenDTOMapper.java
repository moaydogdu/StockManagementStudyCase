package com.study.stockmanagementstudycase.security.model.mappers;

import com.study.stockmanagementstudycase.security.model.AccessAndRefreshToken;
import com.study.stockmanagementstudycase.security.model.dto.response.AuthenticationResponse;

public class TokenDTOMapper {

    public static AuthenticationResponse toAuthenticationResponse(
            final AccessAndRefreshToken accessAndRefreshToken
    ) {
        return AuthenticationResponse.builder()
                .accessToken(accessAndRefreshToken.getAccessToken())
                .refreshToken(accessAndRefreshToken.getRefreshToken())
                .build();
    }
}
