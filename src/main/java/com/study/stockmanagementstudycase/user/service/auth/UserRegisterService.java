package com.study.stockmanagementstudycase.user.service.auth;

import com.study.stockmanagementstudycase.auth.model.dto.request.RegisterRequest;
import com.study.stockmanagementstudycase.user.model.User;

public interface UserRegisterService {

    User register(
            final RegisterRequest registerRequest
    );

}
