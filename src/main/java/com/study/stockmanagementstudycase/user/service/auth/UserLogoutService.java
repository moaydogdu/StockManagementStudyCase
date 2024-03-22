package com.study.stockmanagementstudycase.user.service.auth;

import com.study.stockmanagementstudycase.auth.model.dto.request.TokenInvalidateRequest;

public interface UserLogoutService {

    void logout(
            final TokenInvalidateRequest tokenInvalidateRequest
    );

}
