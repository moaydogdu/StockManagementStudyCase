package com.study.stockmanagementstudycase.user.service.auth;

import com.study.stockmanagementstudycase.auth.model.Token;
import com.study.stockmanagementstudycase.auth.model.dto.request.LoginRequest;

public interface UserLoginService {

    Token login(
            final LoginRequest loginRequest
    );

}
