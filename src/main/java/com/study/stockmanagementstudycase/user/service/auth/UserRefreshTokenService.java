package com.study.stockmanagementstudycase.user.service.auth;

import com.study.stockmanagementstudycase.auth.model.Token;
import com.study.stockmanagementstudycase.auth.model.dto.request.TokenRefreshRequest;

public interface UserRefreshTokenService {

    Token refreshAccessToken(
            final TokenRefreshRequest tokenRefreshRequest
    );

}
